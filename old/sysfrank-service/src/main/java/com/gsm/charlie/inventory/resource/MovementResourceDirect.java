package com.gsm.charlie.inventory.resource;

import com.gsm.charlie.inventory.domain.model.EType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class MovementResourceDirect {
    private Long id;
    private EType type;
    private String description;
    private StoreResource store;
    private ScaffoldResource scaffold;
}
