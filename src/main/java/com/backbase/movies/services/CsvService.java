package com.backbase.movies.services;

import com.backbase.movies.models.Nominee;
import com.backbase.movies.repositories.NomineeRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CsvService {

    private final NomineeRepository nomineeRepository;
    private static final String ACADEMY_AWARDS_CSV = "academy_awards.csv";

    @PostConstruct
    public Integer loadDB() {
        try {
            return loadNominees();
        } catch (IOException e) {
            log.error("IOException to load the database with CSV file: {}", e.getMessage());
        } catch (CsvException e) {
            log.error("CsvException to load the database with CSV file: {}", e.getMessage());
        } catch (URISyntaxException e) {
            log.error("URISyntaxException to load the database with CSV file: {}", e.getMessage());
        }
        return 0;
    }

    @Transactional
    private Integer loadNominees() throws IOException, CsvException, URISyntaxException {
        List<String[]> lines = getLines();
        List<Nominee> nominees = lines.stream()
                .map(CsvService::map)
                .collect(Collectors.toList());

        log.info("saving {} nominees", nominees.size());
        nomineeRepository.saveAll(nominees);
        return nominees.size();
    }

    private List<String[]> getLines() throws IOException, CsvException {
        log.info("Loading the CVS file");

        var resource = ResourceUtils.getURL("classpath:" + ACADEMY_AWARDS_CSV);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(resource.openStream())
        );
        Reader reader = bufferedReader;

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(false)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(1)
                .withCSVParser(csvParser)
                .build();

        log.info("Parsing the CVS to List of");
        List<String[]> lines = csvReader.readAll();

        reader.close();
        csvReader.close();

        return lines;
    }

    private static Nominee map(String[] line) {
        return Nominee.builder()
                .yearOfNominated(line[0])
                .category(line[1])
                .nominated(line[2])
                .additionalInfo(line[3])
                .won("YES".equalsIgnoreCase(line[4]))
                .build();
    }

    @Transactional
    public void clean() {
        nomineeRepository.deleteAll();
    }

}
