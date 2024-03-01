package com.hospital.services;

import static com.hospital.dtos.EmployeeUserDataBacking.createEmployee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.custom_exception.EmployeeAlreadyExistsException;
import com.hospital.custom_exception.NoSuchEmployeeExistsException;
import com.hospital.daos.IDoctorDao;
import com.hospital.daos.IEmployeeDao;
import com.hospital.daos.IUserDao;
import com.hospital.dtos.EmployeeUserDataBacking;
import com.hospital.entities.Employee;
import com.hospital.entities.Patient;
import com.hospital.entities.User;

@Service
@Transactional
public class EmployeeServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IDoctorDao doctorDao;

	// ************fuction to add new employee
	public int addEmployee(EmployeeUserDataBacking userData) {

		if (!userDao.existsByEmail(userData.getEmail())) {
			if (userData.getRole().equalsIgnoreCase("doctor")) {

				userDao.insertIntoUsers(0, userData.getFirstName(), userData.getLastName(), userData.getEmail(),
						userData.getPassword(), userData.getCellNo(), userData.getRole(),
						userData.getSecurityQuestion(), userData.getSecurityAnswer());
				User user = userDao.findByEmail(userData.getEmail());// to get userId
				System.out.println("user id : " + user.getId());
				int updateCount = employeeDao.insertIntoEmployeesTable(0, user.getId(), userData.getDob(),
						userData.getHireDate(), userData.getSalary());
				int empId = employeeDao.getEmpIdByEmail(userData.getEmail());
				System.out.println("emp id : " + empId);
				doctorDao.insertIntoDoctorTable(0, empId, userData.getDoctorCharges());
				return updateCount;
			} else {
				userDao.insertIntoUsers(0, userData.getFirstName(), userData.getLastName(), userData.getEmail(),
						userData.getPassword(), userData.getCellNo(), userData.getRole(),
						userData.getSecurityQuestion(), userData.getSecurityAnswer());
				User user = userDao.findByEmail(userData.getEmail());// to get userId
				int updateCount = employeeDao.insertIntoEmployeesTable(0, user.getId(), userData.getDob(),
						userData.getHireDate(), userData.getSalary());
				return updateCount;
			}

		} else {
			throw new EmployeeAlreadyExistsException(
					"employee with email " + userData.getEmail() + " already exists!!!");
		}

	}

	public List<EmployeeUserDataBacking> getAllEmployees() {
		List<Employee> employees = employeeDao.findAll();
		List<EmployeeUserDataBacking> employeeDetails = createEmployee(employees);
		return employeeDetails;
	}

	public void updateEmployee(EmployeeUserDataBacking userData) throws NoSuchEmployeeExistsException {

		if (employeeDao.existsById(userData.getEmpId())) {
			Employee employeeToUpdate = employeeDao.getById(userData.getEmpId());

			int updateCount = userDao.updateFirstNameLastNameDobCellNo(userData.getFirstName(), userData.getLastName(),
					userData.getDob(), userData.getCellNo(), employeeToUpdate.getUser().getId());
			System.out.println("updated row : " + updateCount);

		} else {
			throw new NoSuchEmployeeExistsException("Employee with id = " + userData.getEmpId() + " does not exist");
		}

	}

	public int deleteUserByCellNoAndUserId(EmployeeUserDataBacking userData) throws NoSuchEmployeeExistsException {

		if (employeeDao.existsById(userData.getEmpId())) {

			Employee employeeToDelete = employeeDao.getById(userData.getEmpId());

			if (employeeToDelete.getUser().getRole().equals("doctor")) {
				System.out.println("---nside doctor delete");
				List<Patient> patients = employeeToDelete.getDoctor().getPatients();
				if (patients.isEmpty()) {
					employeeDao.deleteById(userData.getEmpId());
					return 1;
				}
			} else {
				employeeDao.deleteById(userData.getEmpId());
				System.out.println("no of employees deleted : ");
				return 1;
			}

		} else {
			throw new NoSuchEmployeeExistsException("Employee with id = " + userData.getEmpId() + " does not exist");
		}
		return 0;

	}

}
