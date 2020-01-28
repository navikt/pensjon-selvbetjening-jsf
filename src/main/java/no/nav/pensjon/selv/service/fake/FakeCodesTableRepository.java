package no.nav.pensjon.selv.service.fake;

import no.stelvio.common.codestable.CodesTablePeriodicItem;
import no.stelvio.common.codestable.support.AbstractCodesTableItem;
import no.stelvio.common.codestable.support.IdAsKeyCodesTablePeriodicItem;
import no.stelvio.repository.codestable.CodesTableRepository;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class FakeCodesTableRepository implements CodesTableRepository {

    @Override
    public <T extends AbstractCodesTableItem<? extends Enum, V>, V> List<T> findCodesTableItems(Class<T> aClass) {
        ArrayList<T> list = new ArrayList<>();

        try {
            list.add(newItem(aClass, "ALDER", "ALDER"));
            list.add(newItem(aClass, "OPPRETTET", "OPPRETTET"));
            list.add(newItem(aClass, "UGIFT", "UGIFT"));
            list.add(newItem(aClass, "NB", "NB"));
            list.add(newItem(aClass, "NN", "NN"));
            list.add(newItem(aClass, "EN", "EN"));
            list.add(newItem(aClass, "AP", "AP")); // ElektroniskSkjemaTypeCti
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }

    private <T extends AbstractCodesTableItem<? extends Enum, V>, V> T newItem(Class<T> aClass, String id, String code) throws InstantiationException, IllegalAccessException {
        T t = aClass.newInstance();

        if (t instanceof CodesTablePeriodicItem) { // e.g. UttaksgradCti
            CodesTablePeriodicItem item = (CodesTablePeriodicItem) t;
            item.setCodeAsString(code);
            setDecode(item);
            return (T) item;
        }

        IdAsKeyCodesTablePeriodicItem item = (IdAsKeyCodesTablePeriodicItem) t;
        item.setId(id);
        item.setCodeAsString(code);
        return (T) item;
    }

    public static void setDecode(Object obj) {
        try {
            Field decode = obj.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("decode");
            decode.setAccessible(true);
            String value = obj instanceof CodesTablePeriodicItem ? ((CodesTablePeriodicItem) obj).getCodeAsString() : "DECODE";
            decode.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }
}
