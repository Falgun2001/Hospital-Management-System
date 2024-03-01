package com.hospital.services;

import static com.hospital.dtos.DoctorDataBackinBean.createDoctorsList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.custom_exception.NoSuchPatientFoundException;
import com.hospital.daos.IDoctorDao;
import com.hospital.daos.IEmployeeDao;
import com.hospital.daos.IPatientDao;
import com.hospital.daos.IUserDao;
import com.hospital.dtos.DoctorDataBackinBean;
import com.hospital.dtos.PatientDataBacking;
import com.hospital.entities.Doctor;

@Service
@Transactional
public class DoctorServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IDoctorDao doctorDao;
	@Autowired
	IPatientDao patientDao;

	public List<DoctorDataBackinBean> getAllDoctors() {
		List<Doctor> doctors = doctorDao.findAll();
		List<DoctorDataBackinBean> doctorDetail = createDoctorsList(doctors);

		return doctorDetail;

	}

	public void updatePatientDetails(PatientDataBacking patientData) throws NoSuchPatientFoundException {
		int updateCount;
		if (patientDao.existsById(patientData.getPatId()))
			updateCount = patientDao.updatePatientPrescription(patientData.getPrescription(), patientData.getPatId());
		else
			throw new NoSuchPatientFoundException("patient  with id " + patientData.getPatId() + " does not exists");
	}

}
