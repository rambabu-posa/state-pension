/*
 * Copyright 2017 HM Revenue & Customs
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

package uk.gov.hmrc.statepension.events

import uk.gov.hmrc.domain.Nino
import uk.gov.hmrc.play.audit.model.DataEvent
import uk.gov.hmrc.http.HeaderCarrier

abstract class BusinessEvent(auditType: String, nino: Nino, detail: Map[String, String])(implicit hc: HeaderCarrier)
  extends DataEvent(auditSource = "state-pension", auditType = auditType, detail = detail + ("nino" -> nino.value), tags = Map(
    "X-Request-ID" -> hc.requestId.map(_.value).getOrElse(""),
    "X-Client-ID" -> hc.headers.find(_._1 == "X-Client-ID").map(_._2).getOrElse("")
  ))
