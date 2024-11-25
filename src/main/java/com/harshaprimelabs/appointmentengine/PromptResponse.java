package com.harshaprimelabs.appointmentengine;

import java.util.List;

public record PromptResponse(
        List<Candidate> candidates,
        UsageMetadata usageMetadata,
        String modelVersion
) {}

record Candidate(
        Content content,
        String finishReason,
        Double avgLogprobs
) {}

record Content(
        List<Part> parts,
        String role
) {}

record Part(
        String text
) {}

record UsageMetadata(
        Integer promptTokenCount,
        Integer candidatesTokenCount,
        Integer totalTokenCount
) {}
