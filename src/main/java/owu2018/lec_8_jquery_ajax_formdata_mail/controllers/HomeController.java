package owu2018.lec_8_jquery_ajax_formdata_mail.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import owu2018.lec_8_jquery_ajax_formdata_mail.models.Contact;
import owu2018.lec_8_jquery_ajax_formdata_mail.models.Phone;
import owu2018.lec_8_jquery_ajax_formdata_mail.services.ContactService;
import owu2018.lec_8_jquery_ajax_formdata_mail.services.EmailService;
import owu2018.lec_8_jquery_ajax_formdata_mail.services.PhoneService;
import owu2018.lec_8_jquery_ajax_formdata_mail.services.editors.PhoneEditor;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {

    private ContactService contactService;
    private PhoneService phoneService;

    @GetMapping("/")
    public String home(Model model,
                       @AuthenticationPrincipal Authentication authentication,
                       @AuthenticationPrincipal Principal principal,
                       @AuthenticationPrincipal UserDetails userDetails) {

        System.out.println("1" + " " + authentication.getName());
        System.out.println("1" + " " + principal.getName());
        System.out.println("1" + " " + userDetails.getUsername());
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("contact", new Contact("test", "test@test.com"));
        model.addAttribute("xxx", "hello page");
        return "homeAsyncImage";
    }

    @PostMapping("/saveContact")
    public String saveContact(@Valid Contact contact, BindingResult bindingResult,
                              @RequestParam("picture") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "home";
        }
        contactService.transferFile(file);
        contact.setAvatar(file.getOriginalFilename());
        System.out.println(contact.getPhoneList());
        contactService.save(contact);
        return "redirect:/";
    }

    @GetMapping("/details-{xxx}")
    public String contactDetails(@PathVariable("xxx") int id, Model model) {
        Contact one = contactService.getOne(id);
        model.addAttribute("contact", one);
        return "contactDetails";
    }

    @PostMapping("/update")
    public String updateContact(Contact contact) {
        contactService.save(contact);
        return "redirect:/";
    }

    @Autowired
    private PhoneEditor phoneDoctor;

    @InitBinder("contact")
    public void initBinder(WebDataBinder binder) {
        System.out.println("!!!!!!!!!!!!");
        binder.registerCustomEditor(Phone.class, phoneDoctor);
//        binder.registerCustomEditor(Phone.class,"phoneList", phoneDoctor);

    }

   @GetMapping("/admin")
   public String index(){
       return "about";
   }


}
