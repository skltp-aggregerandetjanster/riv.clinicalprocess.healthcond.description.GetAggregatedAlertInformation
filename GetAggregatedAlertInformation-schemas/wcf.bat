@REM Licensed to the soi-toolkit project under one or more
@REM contributor license agreements.  See the NOTICE file distributed with
@REM this work for additional information regarding copyright ownership.
@REM The soi-toolkit project licenses this file to You under the Apache License, Version 2.0
@REM (the "License"); you may not use this file except in compliance with
@REM the License.  You may obtain a copy of the License at
@REM
@REM     http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
	
@REM ---------------------------------------------------------------------------------
@REM Generates c# WCF service contracts (interface), client proxies and wcf config
@REM file for the WSDLs + XML Schemas of the service domain, using .Net WCF tool svcuti.exe
@REM ---------------------------------------------------------------------------------	
	
	CD ..
	
	SET SCHEMADIR=schemas
		
	SET X0=%SCHEMADIR%/schemas/core_components/clinicalprocess_healthcond_description_2.1.xsd 	
	SET X1=%SCHEMADIR%/schemas/core_components/clinicalprocess_healthcond_description_enum_2.1.xsd 	
	SET X2=%SCHEMADIR%/schemas/core_components/itintegration_registry_1.0.xsd 	
	SET X3=%SCHEMADIR%/schemas/interactions/GetAlertInformationInteraction/GetAlertInformationInteraction_2.0_RIVTABP21.wsdl 	
	SET X4=%SCHEMADIR%/schemas/interactions/GetAlertInformationInteraction/GetAlertInformationResponder_2.0.xsd 	
	SET X5=%SCHEMADIR%/schemas/interactions/GetCareDocumentationInteraction/GetCareDocumentationInteraction_2.1_RIVTABP21.wsdl 	
	SET X6=%SCHEMADIR%/schemas/interactions/GetCareDocumentationInteraction/GetCareDocumentationResponder_2.1.xsd 	
	SET X7=%SCHEMADIR%/schemas/interactions/GetDiagnosisInteraction/GetDiagnosisInteraction_2.0_RIVTABP21.wsdl 	
	SET X8=%SCHEMADIR%/schemas/interactions/GetDiagnosisInteraction/GetDiagnosisResponder_2.0.xsd 	
	SET X9=%SCHEMADIR%/schemas/interactions/GetFunctionalStatusInteraction/GetFunctionalStatusInteraction_2.0_RIVTABP21.wsdl 	
	SET X10=%SCHEMADIR%/schemas/interactions/GetFunctionalStatusInteraction/GetFunctionalStatusResponder_2.0.xsd 
	SET SCHEMAS=%X0% %X1% %X2% %X3% %X4% %X5% %X6% %X7% %X8% %X9% %X10%  
	SET OUTFILE=/out:wcf/generated-src/GetAggregatedAlertInformationClient.cs
	SET APPCONFIG=/config:wcf/generated-src/app.config
	SET NAMESPACE=/namespace:*,GetAggregatedAlertInformation.Schemas
	
	@REM ServiceModel Metadata Utility Tool
	SET SVCUTIL="svcutil.exe"
	%SVCUTIL% /language:cs %OUTFILE% %APPCONFIG% %NAMESPACE% %SCHEMAS%