package ru.langboost.controllers.dictionary;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.langboost.controllers.AbstractController;
import ru.langboost.controllers.message.Message;
import ru.langboost.controllers.message.MessageType;
import ru.langboost.domain.dictionary.Dictionary;
import ru.langboost.domain.user.User;
import ru.langboost.security.AuthenticationService;
import ru.langboost.services.ServiceException;
import ru.langboost.services.profile.ProfileService;
import ru.langboost.services.dictionary.DictionaryService;

import javax.inject.Inject;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Controller
@RequestMapping
public class DictionaryController extends AbstractController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private ProfileService profileService;

    @Inject
    private DictionaryService dictionaryService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/dictionary", method = RequestMethod.GET)
    public String dictionaries(Model model) {
        User user = authenticationService.getPrincipal();
        model.addAttribute("dictionaries",dictionaryService.getDictionaries(user.getProfile()));
        return "dictionary/dictionaries";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/dictionary/save", method = RequestMethod.POST)
    public String addNewDictionary(String title, RedirectAttributes redirectAttributes) {
        if(title == null || title.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Укажите название словаря!"), redirectAttributes);
        } else {
            User user = authenticationService.getPrincipal();
            try {
                dictionaryService.createNewDictionary(title, user.getProfile());
                addFlashMessage(new Message(MessageType.SUCCESS,"Словарь '"+title+"' успешно создан!"),redirectAttributes);
            } catch (ServiceException e) {
                addFlashMessage(e, redirectAttributes);
            }
        }
        return "redirect:/dictionary";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/dictionary/{id}", method = RequestMethod.GET)
    public String dictionary(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Dictionary dictionary = dictionaryService.getDictionary(id);
        if(dictionary == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Словарь не найден"), redirectAttributes);
            return "redirect:/dictionary";
        }
        model.addAttribute("dictionary",dictionary);
        model.addAttribute("words",dictionaryService.getWords(dictionary));
        return "dictionary/dictionary";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/word/save", method = RequestMethod.POST)
    public String addNewWord(String content,Long dictionaryId, RedirectAttributes redirectAttributes) {
        if(content == null || content.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Введите слово!"), redirectAttributes);
            return "redirect:/dictionary/"+dictionaryId;
        }
        Dictionary dictionary = dictionaryService.getDictionary(dictionaryId);
        if(dictionary == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Словарь не найден!"), redirectAttributes);
            return "redirect:/dictionary";
        }
        try {
            dictionaryService.addWord(content, dictionary);
            addFlashMessage(new Message(MessageType.SUCCESS,"Слово '"+content+"' успешно добавлено!"),redirectAttributes);
        } catch (ServiceException e) {
            addFlashMessage(e, redirectAttributes);
        }

        return "redirect:/dictionary/"+dictionaryId;
    }

}
