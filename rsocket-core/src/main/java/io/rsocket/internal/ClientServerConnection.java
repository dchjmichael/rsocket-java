/*
 * Copyright 2015-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rsocket.internal;

import io.rsocket.DuplexConnection;
import io.rsocket.resume.ResumeAwareConnection;
import io.rsocket.resume.ResumeStateHolder;
import io.rsocket.util.DuplexConnectionProxy;

class ClientServerConnection extends DuplexConnectionProxy implements ResumeAwareConnection {

  private final DuplexConnection resumeAware;

  public ClientServerConnection(DuplexConnection delegate, DuplexConnection resumeAware) {
    super(delegate);
    this.resumeAware = resumeAware;
  }

  @Override
  public void acceptResumeState(ResumeStateHolder resumeStateHolder) {
    if (resumeAware instanceof ResumeAwareConnection) {
      ((ResumeAwareConnection) resumeAware).acceptResumeState(resumeStateHolder);
    }
  }
}
