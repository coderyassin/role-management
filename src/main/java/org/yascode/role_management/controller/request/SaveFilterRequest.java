package org.yascode.role_management.controller.request;

import org.yascode.role_management.entity.Application;
import org.yascode.role_management.querybuilder.Query;

public record SaveFilterRequest(Query query, Application application, String authority) {
}
