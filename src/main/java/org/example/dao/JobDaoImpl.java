package org.example.dao;

import org.example.config.DataBase;
import org.example.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JobDaoImpl implements JobDao{
    private Connection connection;
    public JobDaoImpl(){
         this.connection = DataBase.getConnection();
    }
    public void createJobTable() {
        String sql="create table jobs(" +
                "id serial primary key ," +
                "position varchar," +
                "profession varchar," +
                "experience int," +
                "description varchar);";

        try(Statement statement=connection.createStatement()){
            statement.executeQuery(sql);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void addJob(Job job) {
        String sql = "insert into jobs(" +
                "position,profession,experience,description)" +
                "values(?,?,?,?);";
        try (PreparedStatement preparedStatement=connection.prepareStatement(sql)) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setInt(3,job.getExperience());
            preparedStatement.setString(4, job.getDescription());
            preparedStatement.executeUpdate();
            System.out.println(job+ " Successfully added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Job getJobById(Long jobId) {
        String sql="Delete from jobs where id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setLong(1,jobId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> result = new ArrayList<>();
        String word = new Scanner(System.in).nextLine();
        String sql =null;
        if (Objects.equals(ascOrDesc,"asc")) {
            sql = " Select * from jobs order by experience asc;";
        }else if (word.equals("desc")) {sql = " Select * from jobs order by experience desc;";}
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, ascOrDesc);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Job job = new Job();
                    job.setId(resultSet.getLong("id"));
                    job.setPosition(resultSet.getString(2));
                    job.setProfession(resultSet.getString(3));
                    job.setExperience(resultSet.getInt(4));
                    job.setDescription(resultSet.getString(5));
                    result.add(job);
                }
                return result;


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }return null;
        }




    public Job getJobByEmployeeId(Long employeeId) {
        String sql = " select profession from employees e join jobs j on e.job_id=jobs.id where e.id =?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Job job = new Job();
                job = new Job(resultSet.getLong(1),resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),resultSet.getString(5));

            }return new Job();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return  null;
    }

    public void deleteDescriptionColumn() {
        String sql="alter table jobs drop column description;";
        try(Statement statement=connection.createStatement()) {
            statement.executeQuery(sql);
            System.out.println("Successfully deleted column");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }
}
