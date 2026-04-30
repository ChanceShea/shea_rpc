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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RpcRequest implements Serializable {

    private String serviceName;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameters;
}
