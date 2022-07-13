package cn.cleanarch.gw.common.gateway.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author li7hai26@gmail.com
 * @date 2021/10/31
 */
@Data
@Validated
public class GatewayRouteDefinition implements Serializable {

    private String id;

    @NotEmpty
    @Valid
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    @Valid
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    @NotNull
    private URI uri;

    private Map<String, Object> metadata = new HashMap<>();

    private int order = 0;

}
