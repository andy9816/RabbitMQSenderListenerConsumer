package io.spring.rabbitMQ.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManuOrder implements Serializable {
    private static final long serialVersionUID = -1138446817700416884L;
    @JsonProperty
    private String orderIdentifier;
    @JsonProperty
    private int orderId;
    @JsonProperty
    private List<String> orderList;
    @JsonProperty
    private String customerName;

}
