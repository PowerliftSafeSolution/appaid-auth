package com.powerliftsafesolution.appaid.ws.io.morphia.dao.impl;

import com.powerliftsafesolution.appaid.ws.io.IOUtils;
import com.powerliftsafesolution.appaid.ws.io.morphia.dao.EmployeeIncidentReportDAO;
import com.powerliftsafesolution.appaid.ws.io.morphia.entity.EmployeeIncidentReportEntity;
import com.powerliftsafesolution.appaid.ws.shared.dto.EmployeeIncidentReportDTO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeIncidentReportDAOImpl extends
        BasicDAO<EmployeeIncidentReportEntity, ObjectId> implements EmployeeIncidentReportDAO {

    @Autowired
    private IOUtils ioUtils;

    @Autowired
    public EmployeeIncidentReportDAOImpl(Datastore datastore){
        super(EmployeeIncidentReportEntity.class, datastore);
    }

    @Override
    public EmployeeIncidentReportDTO getByNameDateTime(String employeeFirstName, String employeeLastName,
                                                       String date, String time) {
        EmployeeIncidentReportDTO returnValue = null;

        Query<EmployeeIncidentReportEntity> query = createQuery().
                field("employeeFirstName").equal(employeeFirstName).
                field("employeeLastName").equal(employeeLastName).
                field("date").equal(date).field("time").equal(time);

        EmployeeIncidentReportEntity employeeIncidentReportEntity = query.get();

        if(employeeIncidentReportEntity != null){
            returnValue = new EmployeeIncidentReportDTO();
            BeanUtils.copyProperties(employeeIncidentReportEntity, returnValue);
        }

        return returnValue;
    }
}
