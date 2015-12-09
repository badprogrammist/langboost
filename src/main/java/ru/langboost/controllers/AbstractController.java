package ru.langboost.controllers;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.langboost.controllers.message.Message;
import ru.langboost.controllers.message.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public class AbstractController {

    public final static String MESSAGES_FLASH_ATTRIBUTE = "messages";

    protected void addFlashMessage(Exception ex, RedirectAttributes redirectAttributes) {
        addFlashMessage(new Message(MessageType.DANGER,ex.getMessage()),redirectAttributes);
    }

    protected void addFlashMessage(Message message, RedirectAttributes redirectAttributes) {
        addFlashMessages(new ArrayList<Message>(){{add(message);}},redirectAttributes);
    }

    protected void addFlashMessages(List<Message> message, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(MESSAGES_FLASH_ATTRIBUTE, message);
    }
}
