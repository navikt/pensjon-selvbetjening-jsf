package no.nav.presentation.pensjon.pselv.publisering.dinpensjon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import no.stelvio.common.util.DateUtil;
import no.stelvio.common.util.DateUtil.DateCreator;
import no.stelvio.domain.person.Pid;
import no.stelvio.presentation.util.PagedSortableList;

import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SkjemaStatusCode;
import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.simulering.Pensjonsperiode;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.PensjonsKalkulatorConstants;
import no.nav.presentation.pensjon.pselv.common.PselvUtil;
import no.nav.presentation.pensjon.pselv.common.utils.NameUtil;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.support.ByggeklossHelper;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.support.DinPensjonConstants;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.support.GraphTableData;
import no.nav.presentation.pensjon.pselv.publisering.support.ByggeklossFormData;
import no.nav.presentation.pensjon.pselv.tag.PSELVSecurityFunctions;

/**
 * Form populator class for module PUS002 Din pensjon. Is responsible for filling the form with appropriate data.
 */
public class DinPensjonFormPopulator {

    private static final Log LOGGER = LogFactory.getLog(DinPensjonFormPopulator.class);
    private MessageSource messageSource;
    private DateCreator dateCreator = new DateUtil.DefaultDateCreator();


    /**
     * Orchestrates the population of the form object used in the view "Din Pensjon"
     */
    public void populateForm(DinPensjonForm dinPensjonForm, List<KravHode> kravHodeList,
                             List<Skjema> skjemaList, List<Sak> lopendeSakList, List<Vedtak> lopenedeAlderspensjonList) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In method populateForm()");
        }

        // Init person related data:
        populatePersonData(dinPensjonForm);

        dinPensjonForm.setPagedSortableSkjemaList(populateSakSkjemaListe(dinPensjonForm, skjemaList, kravHodeList));
        dinPensjonForm.setLopendeSak(getLopendeSak(lopendeSakList));
        dinPensjonForm.setLopendeAlderspensjon(getLopendeAlderspensjon(lopenedeAlderspensjonList));
        dinPensjonForm.setLessThanLimitMonthsUntilUserIs62(isLessThanLimitMonthsUntilUserIs62(dinPensjonForm));
        ByggeklossHelper byggeklossPopulator = new ByggeklossHelper();
        List<ByggeklossFormData> byggeklossList = byggeklossPopulator.initByggeklossList(dinPensjonForm);
        dinPensjonForm.setByggeklossList(byggeklossList);
        dinPensjonForm.setHelpKey(getHelpKey(dinPensjonForm));
    }

    /**
     * Populates the form with values based on the result from the simulations of alderspensjon each given as a list of
     * pensjonsperioder. A message is shown to the user if first possible uttak of alderspensjon is later than the first
     * calculated simulation age. Input to the graph and graph table is populated based on the simulation result.
     */
    void populateSimuleringResult(DinPensjonForm form, List<List<Pensjonsperiode>> simulationResults,
                                  int firstCalculatedSimulationAge) {
        //TODO restore
//        boolean showMessageForsteMuligUttak = isFirstInnvilgetAlderspensjonAgeEqualsGivenAge(simulationResults,
//                firstCalculatedSimulationAge);
//        form.setShowMessageForsteMuligUtttak(showMessageForsteMuligUttak);
//
//        List<GraphTableData> inputGraphTable = createInputGraphTable(simulationResults, form.getBruker().getPid());
//        form.setGraphTableDataList(inputGraphTable);
//
//        XYSeriesCollection inputGraph = createInputGraph(simulationResults);
//        form.setInputGraph(inputGraph);
//
//        // update form values
//        form.setShowGraph(true);
//        form.setShowFremdriftsindikator(false);
    }

    private Integer getCalenarParameter(Pid pid, Integer parameter) {
        Calendar calendar = Calendar.getInstance();
        Date date = pid.getFodselsdato();
        calendar.setTime(date);
        return calendar.get(parameter);
    }

    /**
     * Return true if the given age is equals the age of the first pensjonsperiode from the simulation results, and false
     * otherwise.
     */
    private boolean isFirstInnvilgetAlderspensjonAgeEqualsGivenAge(List<List<Pensjonsperiode>> simulationResults, int age) {
        return age != simulationResults.get(0).get(0).getAlder();
    }

//    /**
//     * Creates objects for presenting the given simulation result in a table. The table contains one element for each uttak of
//     * alderspensjon. The simulation result represented as a list of pensjonsperiode which each contain age and value.
//     *
//     * @param simulationResults List of results from the simulation of alderspensjon
//     * @param pid Personal identification of logged in user
//     * @return List of {@link GraphTableData}
//     */
//    private List<GraphTableData> createInputGraphTable(List<List<Pensjonsperiode>> simulationResults, Pid pid) {
//        List<GraphTableData> inputGraphTable = new ArrayList<>();
//        for (List<Pensjonsperiode> pensjonsperiodeList : simulationResults) {
//            Pensjonsperiode pensjonsperiode = pensjonsperiodeList.get(0);
//            int yearOfBirth = getCalenarParameter(pid, Calendar.YEAR);
//            int pensjonsYear = yearOfBirth + pensjonsperiode.getAlder();
//            if (Calendar.DECEMBER == getCalenarParameter(pid, Calendar.MONTH)) {
//                pensjonsYear += 1;
//            }
//            GraphTableData grafData = createGraphTableData(pensjonsYear, pensjonsperiode.getBelop(), Integer
//                    .toString(pensjonsperiode.getAlder()));
//            inputGraphTable.add(grafData);
//        }
//        return inputGraphTable;
//    }

//    /**
//     * Create object for presenting the given simulation results as a graph. The simulation result represented as a list of
//     * pensjonsperiode which each contain age and value.
//     *
//     * @param simulationResults List of results from the simulation of alderspensjon
//     * @return {@link XYSeriesCollection }
//     */
//    private XYSeriesCollection createInputGraph(List<List<Pensjonsperiode>> simulationResults) {
//        XYSeriesCollection inputGraph = new XYSeriesCollection();
//        inputGraph.setIntervalPositionFactor(DinPensjonConstants.INTERVAL_POSITION_FACTOR);
//
//        for (List<Pensjonsperiode> pensjonsperiodeList : simulationResults) {
//            XYSeries serie = createGraphDataSeries(pensjonsperiodeList);
//            inputGraph.addSeries(serie);
//        }
//        return inputGraph;
//    }
//
//    /**
//     * Create a series of graph data for a given age option.
//     *
//     * @param pensjonsperiodeList The list of pensjonsperiode for the given age option, will be an empty list if the given age is not selected
//     * by user in view.
//     * @return A series of graph data, or null if one of the preconditions for creating one, was not set.
//     */
//    private XYSeries createGraphDataSeries(List<Pensjonsperiode> pensjonsperiodeList) {
//        int startAge = pensjonsperiodeList.get(0).getAlder();
//
//        XYSeries serie = new XYSeries(getMsg(DinPensjonConstants.GRAF_FORKLARING_FULLPENSJON, new Object[] {startAge}));
//
//        for (int i = DinPensjonConstants.GRAPH_START_YEAR; i < startAge; i++) {
//            // there is no result
//            serie.add(i, DinPensjonConstants.ZERO_PENSJON);
//        }
//
//        Number prevPensjon = DinPensjonConstants.ZERO_PENSJON;
//        for (Pensjonsperiode pensjonsperiode : pensjonsperiodeList) {
//            addToSerie(pensjonsperiode, serie, prevPensjon);
//            prevPensjon = pensjonsperiode.getBelop();
//        }
//
//        return serie;
//    }
//
//    /**
//     * Add pensjon data to a XYSeries object.
//     *
//     * @param pensjonsperiode the pensjonsperiode
//     * @param serie XYSeries serie to add graph data
//     * @param prevPensjon pensjon from last year
//     *
//     */
//    private void addToSerie(Pensjonsperiode pensjonsperiode, XYSeries serie, Number prevPensjon) {
//        Number pensjon = pensjonsperiode.getBelop();
//        Integer alder = pensjonsperiode.getAlder();
//        serie.add(alder, prevPensjon);
//        serie.add(alder, pensjon);
//        if (alder.equals(DinPensjonConstants.GRAPH_END_YEAR)) {
//            serie.add(alder, pensjon);
//            serie.add(alder + 1, pensjon);
//        }
//    }
//
//    /**
//     * Create data to be shown in graph table.
//     */
//    private GraphTableData createGraphTableData(int aar, int aarligPensjon, String alder) {
//        GraphTableData graphTableData = new GraphTableData();
//        graphTableData.setAar(aar);
//        graphTableData.setAarligPensjon(aarligPensjon);
//        graphTableData.setAlder(alder);
//        return graphTableData;
//    }

    /**
     * Populate local form with data for presentating information about user's saker.
     */
    private PagedSortableList<SkjemaKravFormData> populateSakSkjemaListe(DinPensjonForm form, List<Skjema> skjemaList,
                                                                         List<KravHode> kravList) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In method populateSakSkjemaListe()");
        }

        // resetting to default values
        form.getSakerAndSkjemaTablePanel().getDinPensjonDefaultForm(form);

        ArrayList<SkjemaKravFormData> skjemaKravFormDataList = new ArrayList<>();

        // add skjema
        for (Skjema skjema : skjemaList) {
            if (skjema.getSkjemaPselvStatus().isCodeEqualTo(SkjemaStatusCode.OPPRETTET)) {

                skjemaKravFormDataList.add(form.getSakerAndSkjemaTablePanel().populateSkjemaFormData(skjema));

                if (skjema.getSkjemaPselvType().isCodeEqualTo(ElektroniskSkjemaTypeCode.AP)) {
                    form.setAlderspensjonSkjemaId(skjema.getSkjemaId());
                    form.setAlderspensjonSkjemaStarted(true);
                }

                if (skjema.getSkjemaPselvType().isCodeEqualTo(ElektroniskSkjemaTypeCode.FT)) {
                    form.setForsorgertilleggSkjemaId(skjema.getSkjemaId());
                    form.setForsorgertilleggSkjemaStarted(true);
                }
            }
        }

        // add saker
        List<Sak> sakList = form.getSakerAndSkjemaTablePanel().getFilterKravHodeList(kravList);

        for (Sak sak : sakList) {
            if (sak.getSakStatus().isCodeEqualTo(SakStatusCode.TIL_BEHANDLING)
                    && !(sak.getSakType().isCodeEqualTo(SakTypeCode.GENRL) || sak.getSakType().isCodeEqualTo(SakTypeCode.GRBL))) {

                skjemaKravFormDataList.add(form.getSakerAndSkjemaTablePanel().populateSakFormData(sak));
            }
        }

        PagedSortableList<SkjemaKravFormData> pagedSortableList = new PagedSortableList<>(
                skjemaKravFormDataList, DinPensjonConstants.NUMBER_OF_ROWS, DinPensjonConstants.DEFAULT_SORT_COLUMN);

        if (LOGGER.isDebugEnabled()) {
            StringBuilder msg = new StringBuilder();
            msg.append("Number of entries in skjemaList: ");
            msg.append(skjemaList.size());
            msg.append(" Number of entries in kravList: ");
            msg.append(kravList.size());
            msg.append(" Number of entries in formDataList: ");
            msg.append(skjemaKravFormDataList.size());
            LOGGER.debug(msg.toString());
        }

        return pagedSortableList;
    }

    private void populatePersonData(DinPensjonForm dinPensjonForm) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In method populatePersonData()");
        }
        Person person = dinPensjonForm.getBruker();

        // Set the persons fnr
        dinPensjonForm.setFnr(person.getFodselsnummer().trim());

        // Set the persons name
        NameUtil nameUtil = new NameUtil();
        String navn = nameUtil.formatName(person.getFornavn(), person.getMellomnavn(), person.getEtternavn());
        dinPensjonForm.setNavn(navn);
    }

    /**
     * Finds a set of simulation ages based on the given user's current age.
     */
    int[] createListOfSimulationAges(Person bruker) {
        int[] ageList;
        int userAge = getUsersAge(bruker);
        if (isUserAgeUnder62(userAge)) {
            ageList = new int[3];
            ageList[0] = DinPensjonConstants.PENSJONALDER_62;
            ageList[1] = DinPensjonConstants.PENSJONALDER_67;
            ageList[2] = DinPensjonConstants.PENSJONALDER_70;
        } else if (isUserAgeUnder66(userAge)) {
            ageList = new int[3];
            ageList[0] = userAge + 1;
            ageList[1] = DinPensjonConstants.PENSJONALDER_67;
            ageList[2] = DinPensjonConstants.PENSJONALDER_70;
        } else if (isUserAgeUnder69(userAge)) {
            ageList = new int[3];
            ageList[0] = userAge + 1;
            ageList[1] = DinPensjonConstants.PENSJONALDER_70;
            ageList[2] = DinPensjonConstants.PENSJONALDER_75;
        } else {
            ageList = new int[2];
            ageList[0] = userAge + 1;
            ageList[1] = DinPensjonConstants.PENSJONALDER_75;
        }
        return ageList;
    }

    private int getUsersAge(Person bruker) {
        return Pid.calculateAge(bruker.getPid(), dateCreator.createDate());
    }

    private boolean isUserAgeUnder62(int usersAge) {
        return isUserYoungerThan(usersAge, DinPensjonConstants.PENSJONALDER_62);
    }

    private boolean isUserAgeUnder66(int usersAge) {
        return isUserYoungerThan(usersAge, DinPensjonConstants.PENSJONALDER_66);
    }

    private boolean isUserAgeUnder69(int usersAge) {
        return isUserYoungerThan(usersAge, DinPensjonConstants.PENSJONALDER_69);
    }

    private boolean isUserYoungerThan(int usersAge, int compareAge) {
        return usersAge < compareAge;
    }

    private Sak getLopendeSak(List<Sak> lopendeSakList) {
        Sak sak = null;
        if (lopendeSakList != null && lopendeSakList.size() == 1) {
            return lopendeSakList.get(0);
        } else if (lopendeSakList != null && lopendeSakList.size() > 1) {
            for (Sak tempSak : lopendeSakList) {
                if (tempSak.getSakType().isCodeEqualTo(SakTypeCode.ALDER)) {
                    sak = tempSak;
                }
            }
        }
        return sak;
    }

    /**
     * Retrieves the alderspensjons vedtak with latest virkFom.
     */
    private Vedtak getLopendeAlderspensjon(List<Vedtak> lopendeYtelseList) {
        Vedtak gjeldendeVedtak = null;
        for (Vedtak ytelse : lopendeYtelseList) {
            if (gjeldendeVedtak == null) {
                gjeldendeVedtak = ytelse;
            } else if (DateUtil.isAfterByDay(ytelse.getVirkFom(), gjeldendeVedtak.getVirkFom(), true)) {
                gjeldendeVedtak = ytelse;
            }
        }
        return gjeldendeVedtak;
    }

    /**
     * Decide if it is more than x months until the month after user turns 62 years.. This is done by finding the first in month
     * after user turns 67, and substracting limit months from this date. If today's is after that thate, returns true.
     */
    private boolean isLessThanLimitMonthsUntilUserIs62(DinPensjonForm dinPensjonForm) {
        Calendar dateUserIsLimitMonthsFromTurning62 = GregorianCalendar.getInstance();
        dateUserIsLimitMonthsFromTurning62.setTime(dinPensjonForm.getBruker().getPid().getFodselsdato());

        dateUserIsLimitMonthsFromTurning62.add(Calendar.YEAR, PensjonsKalkulatorConstants.AFP_PENSJONSALDER_START);
        // add one since the date is first in month after user is 62
        dateUserIsLimitMonthsFromTurning62.add(Calendar.MONTH, -getSperreVedtakFremITid() + 1);
        dateUserIsLimitMonthsFromTurning62.set(Calendar.DATE, 1);
        dateUserIsLimitMonthsFromTurning62.getTime();
        Calendar now = GregorianCalendar.getInstance();
        return now.after(dateUserIsLimitMonthsFromTurning62);
    }

    private int getSperreVedtakFremITid() {
        return PselvUtil.fetchSperreVedtakFremITid();
    }

    /**
     * Get the correct help key, this is based on the user
     */
    private String getHelpKey(DinPensjonForm dinPensjonForm) {
        String helpKey;
        if (isVariant2()) {
            helpKey = DinPensjonConstants.HELP_KEY_VARIANT_2;
        } else if (dinPensjonForm.isUserBornBefore1943()) {
            helpKey = DinPensjonConstants.HELP_KEY;
        } else if (dinPensjonForm.getLopendeSak() != null) {
            helpKey = DinPensjonConstants.HELP_KEY_LOPENDE;
        } else {
            helpKey = DinPensjonConstants.HELP_KEY_NYTT;
        }
        return helpKey;
    }

    /**
     * Find the view's variant. Unless the user has begrenset fullmakt, the variant to show is variant 1.
     */
    private boolean isVariant2() {
        return PSELVSecurityFunctions.isFullmakt();
    }

    private String getMsg(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
