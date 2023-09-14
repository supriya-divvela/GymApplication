pipeline {
	agent any
	 tools{
        maven 'Maven 3.8.7'
    }
	stages {
		stage("Build Modules & Build Docker Images") {
			steps {
				script {
					def modules = ['GymApplicationStart', 'AuthenticationService','GatewayServer-2','EurekaServer','EmailNotificationService','ReportsMangement']
					for (def module in modules) {
						dir("${module}") {
							echo "Building ${module}..."
							bat "mvn clean install"
						}
					}
				}
			}
		}
	}
}