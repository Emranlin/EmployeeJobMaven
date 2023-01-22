package org.example.services;

import org.example.dao.JobDao;
import org.example.dao.JobDaoImpl;
import org.example.models.Job;

import java.util.List;

public class JobServiceImpl implements JobService {
    JobDao jobDao=new JobDaoImpl();
    @Override
    public void createJobTable() {
        jobDao.createJobTable();

    }

    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);

    }

    @Override
    public Job getJobById(Long jobId) {
        System.out.println(jobDao.getJobById(jobId));

        return null;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        System.out.println(jobDao.sortByExperience(ascOrDesc));
        return null;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        System.out.println(jobDao.getJobByEmployeeId(employeeId));
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();

    }
}
