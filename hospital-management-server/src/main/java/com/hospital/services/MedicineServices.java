package com.hospital.services;

import static com.hospital.dtos.MedicineAssignedDataBackinBean.createAllMedicineList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.daos.IEmployeeDao;
import com.hospital.daos.IMedicineAssignedDao;
import com.hospital.daos.IMedicineDao;
import com.hospital.daos.IUserDao;
import com.hospital.daos.IWardDao;
import com.hospital.dtos.MedicineAssignedDataBackinBean;
import com.hospital.entities.Medicine;

@Service
@Transactional
public class MedicineServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IWardDao wardDao;
	@Autowired
	IMedicineDao medicineDao;
	@Autowired
	IMedicineAssignedDao medicineAssingedDao;

	public List<MedicineAssignedDataBackinBean> getAllMedicines() {
		List<Medicine> medicine = medicineDao.findAll();
		List<MedicineAssignedDataBackinBean> medicinesTosend = createAllMedicineList(medicine);
		return medicinesTosend;

	}

	public int addMedicine(MedicineAssignedDataBackinBean medicineData) {
		return medicineDao.insertIntoMedicineTable(0, medicineData.getMedicineName(), medicineData.getMedicinePrice());

	}

	public void removeMedicine(int medicineId) {
		medicineDao.deleteById(medicineId);

	}

}
