package owu2018.lec_8_jquery_ajax_formdata_mail.services;


import org.springframework.stereotype.Service;
import owu2018.lec_8_jquery_ajax_formdata_mail.dao.PhoneDAO;
import owu2018.lec_8_jquery_ajax_formdata_mail.models.Phone;


@Service
public class PhoneService {

    private PhoneDAO phoneDAO;

    public PhoneService(PhoneDAO phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    public void save(Phone phone) {
        phoneDAO.save(phone);
    }

}
