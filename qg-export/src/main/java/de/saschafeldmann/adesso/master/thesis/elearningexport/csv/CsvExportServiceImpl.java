package de.saschafeldmann.adesso.master.thesis.elearningexport.csv;

import com.google.common.base.Joiner;
import de.saschafeldmann.adesso.master.thesis.elearningexport.ExportService;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.util.csv.CsvWriter;
import de.saschafeldmann.adesso.master.thesis.util.csv.CsvWriterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  10.08.2016
 * Author:         Sascha Feldmann (sascha.feldmann@gmx.de)
 * <br /><br />
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 * <br /><br />
 * Company:
 * adesso AG
 * <br /><br />
 * Generates CSV files with the rows: learning content title, test question, multiple choice mode, correct answer, alternative correct answer, wrong answer, original sentence
 */
@Component
@Scope("prototype")
public class CsvExportServiceImpl implements ExportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvExportServiceImpl.class);
    private static final Joiner CSV_EXPORT_MULTIPLE_VALUES_IN_ONE_COLUMN_JOINER = Joiner.on(",").skipNulls();

    private String learningContentTitleLabel;
    private String generatedQuestionTitleLabel;
    private String multipleChoiceModeLabel;
    private String correctAnswerLabel;
    private String correctAnswersLabel;
    private String wrongAnswersLabel;
    private String originalSentenceLabel;
    private String fileDirectory;
    private String fileName;
    private String isMultipleChoiceLabel;
    private String isNoMultipleChoiceLabel;

    /**
     * Sets the is multiple choice value.
     *
     * @param isMultipleChoiceLabel String
     */
    public void setIsMultipleChoiceLabel(String isMultipleChoiceLabel) {
        this.isMultipleChoiceLabel = isMultipleChoiceLabel;
    }

    /**
     * Sets the is short answer question label.
     *
     * @param isNoMultipleChoiceLabel String
     */
    public void setIsNoMultipleChoiceLabel(String isNoMultipleChoiceLabel) {
        this.isNoMultipleChoiceLabel = isNoMultipleChoiceLabel;
    }

    /**
     * Sets the file's directory.
     *
     * @param fileDirectory String
     */
    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * Sets the filename.
     *
     * @param fileName String
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the generated question label.
     *
     * @param generatedQuestionTitleLabel String
     */
    public void setGeneratedQuestionTitleLabel(String generatedQuestionTitleLabel) {
        this.generatedQuestionTitleLabel = generatedQuestionTitleLabel;
    }

    /**
     * Sets the multiple choice label (whether question is single or multiple choice)
     *
     * @param multipleChoiceModeLabel String
     */
    public void setMultipleChoiceModeLabel(String multipleChoiceModeLabel) {
        this.multipleChoiceModeLabel = multipleChoiceModeLabel;
    }

    /**
     * Sets the correct answer label.
     *
     * @param correctAnswerLabel String
     */
    public void setCorrectAnswerLabel(String correctAnswerLabel) {
        this.correctAnswerLabel = correctAnswerLabel;
    }

    /**
     * Sets the alternative correct answer label.
     *
     * @param correctAnswersLabel String
     */
    public void setCorrectAnswersLabel(String correctAnswersLabel) {
        this.correctAnswersLabel = correctAnswersLabel;
    }

    /**
     * Sets the wrong answer label.
     *
     * @param wrongAnswersLabel String
     */
    public void setWrongAnswersLabel(String wrongAnswersLabel) {
        this.wrongAnswersLabel = wrongAnswersLabel;
    }

    /**
     * Sets the original sentence label.
     *
     * @param originalSentenceLabel String
     */
    public void setOriginalSentenceLabel(String originalSentenceLabel) {
        this.originalSentenceLabel = originalSentenceLabel;
    }

    /**
     * Sets the learning content title label.
     *
     * @param learningContentTitleLabel String
     */
    public void setLearningContentTitleLabel(String learningContentTitleLabel) {
        this.learningContentTitleLabel = learningContentTitleLabel;
    }

    @Override
    public File exportGeneratedQuestionsToFile(Map<LearningContent, List<TestQuestion>> generatedQuestionsMap) {
        LOGGER.info("exportGeneratedQuestionsToFile(): exporting test questions to CSV...");

        CsvWriter csvWriter = new CsvWriterImpl();
        addHeaderColumnsToCsvWriter(csvWriter);
        addGeneratedQuestionsToCsvWriter(csvWriter, generatedQuestionsMap);

        return csvWriter.writeToFile(fileDirectory, fileName);
    }

    private void addHeaderColumnsToCsvWriter(CsvWriter csvWriter) {
        csvWriter.addRow(
                learningContentTitleLabel,
                generatedQuestionTitleLabel,
                multipleChoiceModeLabel,
                correctAnswerLabel,
                correctAnswersLabel,
                wrongAnswersLabel,
                originalSentenceLabel
        );
    }

    private void addGeneratedQuestionsToCsvWriter(CsvWriter csvWriter, Map<LearningContent, List<TestQuestion>> generatedQuestionsMap) {
        for (final LearningContent learningContent : generatedQuestionsMap.keySet()) {
            final String columnLearningContentTitle = learningContent.getTitle();

            for (final TestQuestion testQuestion : generatedQuestionsMap.get(learningContent)) {
                final String columnTestQuestionQuestion = testQuestion.getQuestion();
                final String columnTestQuestionOriginalSentence = testQuestion.getSourceConcept().getOriginalSentence();
                final String columnTestQuestionCorrectAnswer = testQuestion.getCorrectAnswer();
                final String columnTestQuestionAlternativeCorrectAnswers = CSV_EXPORT_MULTIPLE_VALUES_IN_ONE_COLUMN_JOINER.join(testQuestion.getAlternativeCorrectAnswers());
                final String columnTestQuestionAlternativeWrongAnswers = CSV_EXPORT_MULTIPLE_VALUES_IN_ONE_COLUMN_JOINER.join(testQuestion.getAlternativeWrongAnswers());
                final String columnIsMultipleChoice = (testQuestion.isMultipleChoice() ? isMultipleChoiceLabel : isNoMultipleChoiceLabel);

                csvWriter.addRow(
                        columnLearningContentTitle,
                        columnTestQuestionQuestion,
                        columnIsMultipleChoice,
                        columnTestQuestionCorrectAnswer,
                        columnTestQuestionAlternativeCorrectAnswers,
                        columnTestQuestionAlternativeWrongAnswers,
                        columnTestQuestionOriginalSentence);
            }
        }
    }

}
