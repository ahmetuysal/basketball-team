package io.spring.sample.graphql.common;

import java.nio.charset.StandardCharsets;

public class ResolvedGlobalId {

    public ResolvedGlobalId(String type, String id) {
        this.type = type;
        this.id = id;
    }

    private final String type;
    private final String id;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    private static final java.util.Base64.Encoder encoder = java.util.Base64.getUrlEncoder().withoutPadding();
    private static final java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();

    public static String toGlobalId(String type, String id) {
        return encoder.encodeToString((type + ":" + id).getBytes(StandardCharsets.UTF_8));
    }

    public static ResolvedGlobalId fromGlobalId(String globalId) {
        String[] split = new String(decoder.decode(globalId), StandardCharsets.UTF_8).split(":", 2);
        if (split.length != 2) {
            throw new IllegalArgumentException(String.format("expecting a valid global id, got %s", globalId));
        }
        return new ResolvedGlobalId(split[0], split[1]);
    }
}


