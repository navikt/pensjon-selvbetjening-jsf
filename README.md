# pensjon-selvbetjening-jsf
Tjeneste for at brukere selv kan administrere sin pensjon (beregne pensjon, søke om pensjon, se pensjonsrelatert informasjon).
Benyttes også av saksbehandlere for å utføre oppgaver på vegne av brukere.

Dette prosjektet inneholder brukergrensesnittet, implementert med JavaServer Faces (JSF).
Det er basert på Java 11, Maven og Tomcat 8 (embedded).

# Komme i gang

## Forutsetninger

Java 11 og Maven 3.x må være installert.

## Bruk

Git-klon prosjektet, bruk deretter følgende kommandoer for å starte Tomcat-serveren:
```
$ mvn package
$ sh target/bin/webapp
```

Alternativt, åpne prosjektet i et Java-IDE og start serveren ved å kjøre klassen `Application`.

Test at applikasjonen kjører ved å åpne følgende adresse i en nettleser:
```
http://localhost:8080/ping.html
```

Bruk applikasjonen som saksbehandler ved å åpne følgende adresse i en nettleser:
```
http://localhost:8080/dinpensjon?_loggedOnName=<saksbehandlernavn>&_brukerId=<fnr>
```

# Henvendelser

Spørsmål knyttet til koden eller prosjektet kan rettes mot:

* Espen Gjøstøl

E-post: fornavn.etternavn@nav.no

## For NAV-ansatte

Interne henvendelser kan sendes via Slack i kanalen [#team_persondialog2](https://nav-it.slack.com/archives/CMM57SXNW).
