package org.example.repository.impl;

import com.zaxxer.hikari.HikariConfig;
import org.example.db.DataSourceConnectHikari;
import org.example.model.Doctor;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
class DoctorRepositoryImplTest {
    private DoctorRepositoryImpl doctorRepository;
    @Container
    public static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("aston")
                .withUsername("postgres")
                .withInitScript("db-migration.sql")
                .withPassword("469841");
    @BeforeAll
    static void setUp() {
        container.start();
    }
    @BeforeEach
    void setUpHikari(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(container.getJdbcUrl());
        hikariConfig.setPassword(container.getPassword());
        hikariConfig.setUsername(container.getUsername());
        doctorRepository = new DoctorRepositoryImpl(new DataSourceConnectHikari(hikariConfig));
    }
    @AfterAll
    static void stopContainer(){
        container.stop();
    }

    @Test
    void update() {
        Doctor doctor = new Doctor(1L,"nameDoctor","Spec");
        doctorRepository.update(doctor);
        Assertions.assertEquals(doctor.getId(),1L);
        Assertions.assertEquals(doctor.getNameDoctor(),"nameDoctor");

    }
    @Test
    void save() {
        Doctor doctor = new Doctor(5L,"nameDoctor","Spec");
       doctorRepository.save(doctor);
        Assertions.assertEquals(doctor.getNameDoctor(),"nameDoctor");
    }
    @Test
    void deleteById() {
        Assertions.assertTrue(doctorRepository.deleteById(2L));
    }
    //    @Test
//    void findByIdCheckResultSet() throws SQLException {
//        Mockito.when(resultSet.getLong("doctor_id")).thenReturn(Long.valueOf(1));
//        Mockito.when(resultSet.getString("name_doctor")).thenReturn("Иванов");
//        Mockito.when(resultSet.getString("specialization")).thenReturn("Терапевт");
//        Mockito.when(resultSet.getString("name_patient")).thenReturn("Капустин");
//        Mockito.when(resultSet.getLong("clinic_id")).thenReturn(Long.valueOf(1));
//        Mockito.when(resultSet.getString("name_clinic")).thenReturn("Городская поликлиника");
//        try ( //Connection connection = DriverManager.getConnection(container.getJdbcUrl(),container.getUsername(),container.getPassword());)
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                     "select d.doctor_id, d.name_doctor,d.specialization," +
//                     "p.patient_id, p.name_patient, c.clinic_id, c.name_clinic from doctors as d " +
//                     "inner join doctor_patient dp on d.doctor_id = dp.doctor_id " +
//                     "inner join patients p on p.patient_id = dp.patient_id " +
//                     "inner join clinics c on c.doctor_id = d.doctor_id where d.doctor_id = 1 order by p.name_patient")){
//            ResultSet rs = preparedStatement.executeQuery();
//            rs.next();
//            Assert.assertNotNull(rs);
//            Assert.assertEquals(resultSet.getLong("doctor_id"), rs.getLong("doctor_id"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//

//    }
    @Test
    void findById(){
        Doctor doctor = doctorRepository.findById(1L);
        Assertions.assertEquals(doctor.getNameDoctor(),"Иванов");
    }
}