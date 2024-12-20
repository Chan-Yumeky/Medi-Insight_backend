package ynu.mediinsight.MediInsightBackend.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import ynu.mediinsight.MediInsightBackend.dto.response.DoctorVO;
import ynu.mediinsight.MediInsightBackend.dto.response.PatientVO;
import ynu.mediinsight.MediInsightBackend.dto.response.RegistrationVO;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;
import ynu.mediinsight.MediInsightBackend.entity.po.Registration;

import java.util.Date;

public class Proxy {
    public static PatientVO account2Patient(Account account) {
        PatientVO patient = new PatientVO();
        patient.setId(account.getId());
        patient.setUsername(account.getUsername());
        patient.setGender(account.getGender());
        patient.setAge(account.getAge());
        patient.setPhone(account.getPhone());
        patient.setEmail(account.getEmail());
        patient.setAddress(account.getAddress());
        patient.setBirthday(account.getBirthday());
        return patient;
    }

    public static DoctorVO account2Doctor(Account account) {
        DoctorVO doctor = new DoctorVO();
        doctor.setId(account.getId());
        doctor.setUsername(account.getUsername());
        doctor.setGender(account.getGender());
        doctor.setAge(account.getAge());
        doctor.setPhone(account.getPhone());
        doctor.setEmail(account.getEmail());
        doctor.setAddress(account.getAddress());
        doctor.setBirthday(account.getBirthday());
        doctor.setDepartment(account.getDepartment());
        doctor.setIsExpert(account.getIsExpert());
        return doctor;
    }

    public static RegistrationVO registration2RegistrationVO(Registration registration) {
        RegistrationVO registrationVO = new RegistrationVO();
        registrationVO.setId(registration.getId());
        registrationVO.setStatus(registration.getStatus());
        registrationVO.setSource(registration.getSource());
        registrationVO.setBookTime(registration.getBookTime());
        registrationVO.setStartTime(registration.getStartTime());
        registrationVO.setEndTime(registration.getEndTime());
        return registrationVO;
    }
}
