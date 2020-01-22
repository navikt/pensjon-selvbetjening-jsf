package no.nav.presentation.pensjon.pselv.tag;

import java.lang.reflect.Modifier;

import org.apache.myfaces.view.facelets.tag.AbstractTagLibrary;

import static java.util.Arrays.stream;

public final class JsfPSELVSecurityLibrary extends AbstractTagLibrary {

    public static final String NAMESPACE = "http://www.stelvio.no/jsf/pselv/security";
    public static final JsfPSELVSecurityLibrary INSTANCE = new JsfPSELVSecurityLibrary();

    public JsfPSELVSecurityLibrary() {
        super(NAMESPACE);

        try {
            stream(PSELVSecurityFunctions.class.getMethods())
                    .filter(method -> Modifier.isStatic(method.getModifiers()))
                    .forEach(method -> addFunction(method.getName(), method));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
