CREATE TABLE doctors
(
    doctor_id      bigserial   not null primary key,
    name_doctor    varchar(60) not null,
    specialization varchar(60) not null
);
CREATE TABLE clinics
(
    clinic_id   bigserial    not null primary key,
    name_clinic varchar(255) not null,
    doctor_id   bigserial references doctors (doctor_id) on DELETE cascade on UPDATE cascade
);
CREATE TABLE patients
(
    patient_id   bigserial   not null primary key,
    name_patient varchar(60) not null
);
CREATE TABLE doctor_patient
(
    patient_id bigserial references patients (patient_id) on DELETE cascade on UPDATE cascade,
    doctor_id  bigserial references doctors (doctor_id) on DELETE cascade on UPDATE cascade
);

INSERT INTO doctors(doctor_id, name_doctor, specialization)
VALUES ('1', 'Иванов', 'Терапевт'),
       ('2', 'Петров', 'Хирург'),
       ('3', 'Сидоров', 'Психиатр'),
       ('4', 'Королев', 'Отоларинголог');
INSERT INTO clinics(clinic_id, name_clinic, doctor_id)
VALUES ('1', 'Городская поликлиника', '1'),
--        ('1', 'Городская поликлиника', '3'),
       ('2', 'Районная поликлиника', '4'),
       ('3', 'Областная поликлиника', '2');
INSERT INTO patients(patient_id, name_patient)
VALUES ('1', 'Морковкин'),
       ('2', 'Огуречников'),
       ('3', 'Помидоркин'),
       ('4', 'Капустин'),
       ('5', 'Яблочкин');
INSERT INTO doctor_patient(doctor_id, patient_id)
VALUES ('1', '4'),
       ('1', '1'),
       ('3', '2'),
       ('3', '4'),
       ('2', '3'),
       ('2', '5');


