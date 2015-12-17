package ru.langboost.controllers.rule;

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
import ru.langboost.domain.rule.Rule;
import ru.langboost.domain.user.User;
import ru.langboost.security.AuthenticationService;
import ru.langboost.services.ServiceException;
import ru.langboost.services.dictionary.DictionaryService;
import ru.langboost.services.profile.ProfileService;
import ru.langboost.services.rule.RuleService;

import javax.inject.Inject;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Controller
public class RuleController extends AbstractController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private RuleService ruleService;

    @Secured({"ROLE_ADMIN","ROLE_AUTHOR"})
    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public String rules(Model model) {
        model.addAttribute("rules",ruleService.getRules());
        return "rule/rules";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/rule/save", method = RequestMethod.POST)
    public String saveRule(String title, String content, RedirectAttributes redirectAttributes) {
        if(title == null || title.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Укажите название правила!"), redirectAttributes);
            return gotoRules();
        }
        if(content == null || content.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Введите формулировку!"), redirectAttributes);
            return gotoRules();
        }
        try {
            ruleService.createRule(title, content);
            addFlashMessage(new Message(MessageType.SUCCESS,"Правило '"+title+"' успешно создано!"),redirectAttributes);
        } catch (ServiceException e) {
            addFlashMessage(e, redirectAttributes);
        }
        return gotoRules();
    }

    @Secured({"ROLE_ADMIN","ROLE_AUTHOR"})
    @RequestMapping(value = "/rule/{id}", method = RequestMethod.GET)
    public String rule(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Rule rule = ruleService.getRule(id);
        if(rule == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Правило не найдено!"), redirectAttributes);
            return gotoRules();
        }
        model.addAttribute("rule",rule);
        return "rule/rule";
    }


    public static String gotoRules() {
        return "redirect:/rules";
    }

}
