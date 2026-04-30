package com.shea.rpc.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Shea.
 * @since : 2026/4/29 21:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable {

    private Object data;

    private Class<?> dataType;

    private String message;

    private Exception exception;
}
