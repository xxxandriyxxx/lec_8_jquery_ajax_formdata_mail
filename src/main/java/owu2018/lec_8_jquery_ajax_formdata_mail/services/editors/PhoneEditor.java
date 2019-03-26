package owu2018.lec_8_jquery_ajax_formdata_mail.services.editors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import owu2018.lec_8_jquery_ajax_formdata_mail.models.Phone;
import owu2018.lec_8_jquery_ajax_formdata_mail.services.PhoneService;


import java.beans.PropertyEditorSupport;

@Component
public class PhoneEditor extends PropertyEditorSupport {

    @Autowired
    private PhoneService phoneService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Phone phone = new Phone();
        phone.setNumber(text);
        phoneService.save(phone);
        setValue(phone);
    }
}
