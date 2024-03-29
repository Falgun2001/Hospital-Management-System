import { useState } from "react";
import { Badge, Button } from "react-bootstrap";
import { useNavigate } from "react-router";
const PatientDoctor = (props) => {
  const { patient, handleShow,setDataChangedFlag } = props;
  const navigate=useNavigate();
  return (
    <tr>
      <th>{patient.patId}</th>
      <th>{patient.firstName + " " + patient.lastName}</th>
      <th>{patient.paymentStatus}</th>
      <th>
        <Button
          variant="outline-primary"
          size="sm"
          style={{ fontSize: "small" }}
          onClick={() => {
            console.log("empid in patient component" + patient.empId);
            navigate("/doctor/patientDetails",{state:{patientId:patient.patId}})
          }}
        >
         Details
        </Button>
      </th>
      {/* {updateModalFlag && <UpdateEmployeeModal setShow={setShow} setUpdateModalFlag={setUpdateModalFlag} handleClose={handleClose} handleShow={handleShow}/> } */}
    </tr>
  );
};
export default PatientDoctor;
