package com.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.custom_exception.NoSuchMedicineExistsException;
import com.hospital.daos.IEmployeeDao;
import com.hospital.daos.IMedicineAssignedDao;
import com.hospital.daos.IMedicineDao;
import com.hospital.daos.IUserDao;
import com.hospital.daos.IWardDao;
import com.hospital.dtos.MedicineAssignedDataBackinBean;

@Service
@Transactional
public class MedicineAssignedServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IWardDao wardDao;
	@Autowired
	IMedicineAssignedDao medicineAssingedDao;
	@Autowired
	IMedicineDao medicineDao;

	public void addMedicineToPatient(MedicineAssignedDataBackinBean medicineData) throws NoSuchMedicineExistsException {

		medicineAssingedDao.addIntoMedicineAssigned(medicineData.getPatId(), medicineData.getMedicineId(),
				medicineData.getMedicinePrescription(), medicineData.getMedicineQty());

	}

	public void removeMedicineOfPatient(int medicineAssignId) {
		medicineAssingedDao.deleteById(medicineAssignId);
	}

}
