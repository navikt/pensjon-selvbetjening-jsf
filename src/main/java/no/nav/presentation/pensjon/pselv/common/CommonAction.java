package no.nav.presentation.pensjon.pselv.common;

import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.presentation.pensjon.common.taglib.help.HelpUtils;
import no.nav.presentation.pensjon.pselv.common.enums.UserGroup;
import no.nav.presentation.pensjon.pselv.common.session.PselvSessionMapWrapper;
import no.nav.presentation.pensjon.pselv.tag.PSELVSecurityFunctions;

public abstract class CommonAction extends FormAction {

    private boolean refreshCacheEnabled;
    private CommonActionDelegate commonActionDelegate;
    private MessageSource messageSource;

    /**
     * Retrives brukerPid, sakNr, kravId and vedtakId from PsakSessionMapWrapper
     * If the id exist in session, the CommonActionDelegate is called and the
     * object corresponding to the ids are tried to be retrived.
     */
    @Override
    protected Object createFormObject(RequestContext context) throws Exception {
        CommonForm form = (CommonForm) super.createFormObject(context);

        if (commonActionDelegate == null) {
            throw new NullPointerException("commonActionDelegate can not be NULL!");
        }

        if (PselvSessionMapWrapper.get("help") == null
                || !PselvSessionMapWrapper.get("help").equals(HelpUtils.HELP)) {
            //Adding global helpUrl variable to scope
            PselvSessionMapWrapper.put("help", HelpUtils.HELP);
        }
        /* Gets the Bruker (the logged-on user). */
        if (PselvSessionMapWrapper.getBrukerPid() != null) {
            /* Gets the Bruker (the logged-on user). */
            form.setBruker(commonActionDelegate.getPersonBySessionPid(refreshCacheEnabled));
            refreshCacheEnabled = false;
        } else {
            form.setBruker(null); // forenklet simulering
        }

        /*
         * Gets the user group of the logged-on user. The user group is found
         * based on the user's age.
         */
        if (PselvSessionMapWrapper.get(PselvSessionMapWrapper.USER_GROUP) != null) {
            UserGroup userGroup = (UserGroup) PselvSessionMapWrapper.get(PselvSessionMapWrapper.USER_GROUP);
            form.setUserGroup(userGroup);
        } else {
            form.setUserGroup(null); // forenklet simulering
        }

        /* Set internalUser and externalUser */
        form.setInternBruker(PSELVSecurityFunctions.isInternBruker());

        /* Gets the G (grunnbelop). */
        Double grunnbelop = commonActionDelegate.hentCurrentGrunnbelop();
        if (grunnbelop != null) {
            form.setGrunnbelop(grunnbelop);
        }

        form.setShowGlobalMessages(true);

        return form;
    }

    @Override
    protected Object getFormObject(RequestContext context) {
        try {
            return super.getFormObject(context);
        } catch (Exception e) {
            throw new ImplementationUnrecoverableException(e);
        }
    }

    /**
     * Looks up message from properties file, i.e. resources.properties.
     */
    protected String getMsg(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    /**
     * Looks up message from properties file, i.e. resources.properties.
     */
    protected String getMsg(String key, String[] params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }

    public void setCommonActionDelegate(CommonActionDelegate commonActionDelegate) {
        this.commonActionDelegate = commonActionDelegate;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public CommonActionDelegate getCommonActionDelegate() {
        return commonActionDelegate;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void changeListener(String flowTransitionName) {
        RequestContext requestContext = RequestContextHolder.getRequestContext();
        RequestControlContext requestControlContext = (RequestControlContext) requestContext;
        requestControlContext.handleEvent(new Event(this, flowTransitionName));
    }

    protected FacesContext getCurrentFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected RequestContext getRequestContext() {
        return RequestContextHolder.getRequestContext();
    }

    protected boolean isRefreshCacheEnabled() {
        return refreshCacheEnabled;
    }

    protected void setRefreshCacheEnabled(boolean refreshCacheEnabled) {
        this.refreshCacheEnabled = refreshCacheEnabled;
    }
}
