import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    void QuestionParserEmptyInput() {
        QuestionParser questionParser = new QuestionParser();
        Question question = questionParser.parseQuestion("");

        assertNull(question);
    }

    @Test
    void QuestionParserTest() {
        String jsonResponse =
                "{\n"
                        + "  \"candidates\": [\n"
                        + "    {\n"
                        + "      \"content\": {\n"
                        + "        \"role\": \"model\",\n"
                        + "        \"parts\": [\n"
                        + "          {\n"
                        + "            \"text\": \"```json\\n{\\n  \\\"question\\\": \\\"What is the average lifespan of a dog?\\\",\\n  \\\"answers\\\": [\\n    \\\"A. 5-7 years\\\",\\n    \\\"B. 10-13 years\\\",\\n    \\\"C. 15-20 years\\\",\\n    \\\"D. 2-4 years\\\"\\n  ],\\n  \\\"correctAnswer\\\": 1\\n}\\n```\"\n"
                        + "          }\n"
                        + "        ]\n"
                        + "      },\n"
                        + "      \"finishReason\": \"STOP\",\n"
                        + "      \"avgLogprobs\": -0.040963116017254914\n"
                        + "    }\n"
                        + "  ],\n"
                        + "  \"usageMetadata\": {\n"
                        + "    \"promptTokenCount\": 81,\n"
                        + "    \"candidatesTokenCount\": 88,\n"
                        + "    \"totalTokenCount\": 169,\n"
                        + "    \"promptTokensDetails\": [\n"
                        + "      {\n"
                        + "        \"modality\": \"TEXT\",\n"
                        + "        \"tokenCount\": 81\n"
                        + "      }\n"
                        + "    ],\n"
                        + "    \"candidatesTokensDetails\": [\n"
                        + "      {\n"
                        + "        \"modality\": \"TEXT\",\n"
                        + "        \"tokenCount\": 88\n"
                        + "      }\n"
                        + "    ]\n"
                        + "  },\n"
                        + "  \"modelVersion\": \"gemini-2.0-flash-001\",\n"
                        + "  \"createTime\": \"2025-02-24T18:49:34.322504Z\",\n"
                        + "  \"responseId\": \"Pr-8Z8jXE-eemecP5cK5QQ\"\n"
                        + "}";

        QuestionParser questionParser = new QuestionParser();
        Question question = questionParser.parseQuestion(jsonResponse);

        assertEquals("What is the average lifespan of a dog?", question.question);
        assertEquals("B. 10-13 years", question.correctAnswer);
        assertEquals(4, question.options.length);
    }
}
