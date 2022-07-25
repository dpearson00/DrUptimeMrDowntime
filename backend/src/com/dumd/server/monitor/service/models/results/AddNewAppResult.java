package com.dumd.server.monitor.service.models.results;

import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.utils.Status;

public class AddNewAppResult {
    private Status status;
    private ApplicationModel application;

    public AddNewAppResult(Builder builder) {
        this.status = builder.status;
        this.application = builder.application;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ApplicationModel getApplication() {
        return application;
    }

    public void setApplication(ApplicationModel application) {
        this.application = application;
    }

    public static Builder builder() { return new Builder();}

    public static final class Builder {
        private Status status;
        private ApplicationModel application;

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withApplication(ApplicationModel application) {
            this.application = application;
            return this;
        }

        public AddNewAppResult build() { return new AddNewAppResult(this);}
    }
}
