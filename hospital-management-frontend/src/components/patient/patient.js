import { useState } from "react";
import { Badge, Button } from "react-bootstrap";
import { useNavigate } from "react-router";
const Patient = (props) => {
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
            navigate("/reception/patientDetails",{state:{patientId:patient.patId}})
          }}
        >
         Details
        </Button>
      </th>
    </tr>
  );
};
export default Patient;
