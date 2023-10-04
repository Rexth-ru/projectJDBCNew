CREATE TABLE clinics
(
    id          bigserial    not null primary key,
    name_clinic varchar(255) not null
);
CREATE TABLE doctors
(
    id             bigserial   not null primary key,
    name_doctor    varchar(60) not null,
    specialization varchar(60) not null,
    clinic_id      bigserial references clinics (id)
);
CREATE TABLE patients
(
    id           bigserial   not null primary key,
    name_patient varchar(60) not null,
    clinic_id    bigserial references clinics (id)
);
CREATE TABLE doctor_patient
(
    id        bigserial not null primary key,
    patient_id bigserial references patients (id),
    doctor_id bigserial references doctors (id)  on update cascade on delete cascade,
    clinic_id bigserial references clinic (id)
);
INSERT INTO clinic(id, name)
VALUES ('1', 'Городская поликлиника'),
       ('2', 'Районная поликлиника'),
       ('3', 'Областная поликлиника');
INSERT INTO doctors(id, name_doctor, specialization, clinic_id)
VALUES ('1', 'Иванов', 'Терапевт','3'),
       ('2', 'Петров', 'Хирург','3'),
       ('3', 'Сидоров', 'Психиатр','2'),
       ('4', 'Королев', 'Отоларинголог','1');
INSERT INTO patients(id, name_patient, clinic_id)
VALUES ('1', 'Морковкин', '1'),
       ('2', 'Огуречников', '1'),
       ('3', 'Помидоркин', '3'),
       ('4', 'Капустин', '3'),
       ('5', 'Яблочкин', '2');
INSERT INTO doctor_patient(id,  doctor_id, patient_id,clinic_id)
VALUES ('1', '4', '2','1'),
       ('2', '4', '1','1'),
       ('3', '3', '5','2'),
       ('4', '2', '3','3'),
       ('5', '2', '4','3'),
       ('6', '1', '4','3')


