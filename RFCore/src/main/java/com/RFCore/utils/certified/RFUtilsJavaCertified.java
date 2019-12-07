package com.RFCore.utils.certified;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.RFCore.beans.certified.Answer;
import com.RFCore.beans.certified.Question;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author diego
 *
 */
public class RFUtilsJavaCertified {

	private static final String PATH_JAVA_6_TEST_KILLER = "certified/java6/testkiller.pdf";

	private static final String[] IGNORE_WORDS_JAVA_TEST_KILLER_6 = new String[] { "310-065",
			"http://www.testkiller.com", "http://www.troytec.com" };

	private static final Set<String> SET_KEYS_ANSWERS = new LinkedHashSet<String>();

	private static final String KEY_ANSWER = "Answer:";

	private static final String KEY_QUESTION = "QUESTION:";

	private static final String NAME_FILE_JAVA_6_TEST_KILLER_JSON = "testKiller6.json";

	static {
		SET_KEYS_ANSWERS.add("A.");
		SET_KEYS_ANSWERS.add("B.");
		SET_KEYS_ANSWERS.add("C.");
		SET_KEYS_ANSWERS.add("D.");
		SET_KEYS_ANSWERS.add("E.");
		SET_KEYS_ANSWERS.add("F.");
		SET_KEYS_ANSWERS.add("G.");
		SET_KEYS_ANSWERS.add("H.");
		SET_KEYS_ANSWERS.add("I.");
		SET_KEYS_ANSWERS.add("J.");
		SET_KEYS_ANSWERS.add("K.");
	}

	/**
	 * Method to read test killer pdf from java 6 and conver to json
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void readTestKillerJava6AndConvertToJson() throws FileNotFoundException, IOException {
		File file = new File(
				RFUtilsJavaCertified.class.getClassLoader().getResource(PATH_JAVA_6_TEST_KILLER).getFile());

		try (PDDocument document = PDDocument.load(file)) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);

				// split by whitespace
				String lines[] = pdfFileInText.split("\\r?\\n");
				String processLine = null;
				boolean process = true;

				List<Question> questions = new ArrayList<Question>();
				List<Answer> answers = new ArrayList<Answer>();

				Question question = null;
				Answer answer = null;
				String[] successAnswers = null;

				for (String line : lines) {

					// System.out.println(line);

					processLine = line.trim();
					process = true;

					if (processLine.isEmpty()) {
						continue;
					}

					// If line is equals same word not process
					for (String ignoreWord : IGNORE_WORDS_JAVA_TEST_KILLER_6) {
						if (processLine.equals(ignoreWord)) {
							process = false;
							break;
						}
					}

					// If line is number is a page of test killer
					if (process) {
						try {
							Integer.parseInt(processLine);
							process = false;
						} catch (Exception e) {

						}
					}

					if (!process) {
						continue;
					}

					// System.out.println(line);

					if (line.contains(KEY_QUESTION)) {
						if (question != null) {
							if(answer != null && !answers.contains(answer)) {
								answers.add(answer);
							}
							question.setAnswers(answers);
							if (question.getAnswers().size() > 0) {
								questions.add(question);
							}
							answers = new ArrayList<Answer>();
						}
						question = new Question();
						question.setNumber(Integer.parseInt(line.replace(KEY_QUESTION, "").trim()));
						answer = null;
					} else if (line.contains(KEY_ANSWER)) {
						if (question != null) {
							line = line.replace(KEY_ANSWER, "");
							successAnswers = line.split(",");
							for (String succesAnswer : successAnswers) {
								question.getSucessAnswers().add(succesAnswer.trim());
							}
						}
					} else {
						if (line.length() >= 2) {
							if (SET_KEYS_ANSWERS.contains(line.substring(0, 2))) {
								if (answer != null) {
									answers.add(answer);

								}
								answer = new Answer();
								answer.setKey(line.substring(0, 1));

								line = line.substring(2);
							}
						}

						if (answer != null) {
							answer.setText(answer.getText().concat(line).concat("\n"));
						} else if (question != null) {
							question.setText(question.getText().concat(line).concat("\n"));
						}
					}

				}

				System.out.println("Questions: " + questions.size());
				questions.forEach(v -> {

					System.out.println("------------------");
					System.out.println("Question: " + v.getText());

					v.getAnswers().forEach(v2 -> {

						System.out.println("Answers: " + v2.getText());

					});

					System.out.println("Success Answers: " + v.getSucessAnswers().toString());

					System.out.println("------------------");
				});

				ObjectMapper mapper = new ObjectMapper();

				String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(questions);

				System.out.println(jsonInString);

				FileOutputStream outputStream = new FileOutputStream(NAME_FILE_JAVA_6_TEST_KILLER_JSON);
				byte[] strToBytes = jsonInString.getBytes();
				outputStream.write(strToBytes);

				outputStream.close();

			}

		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		readTestKillerJava6AndConvertToJson();
	}
}
