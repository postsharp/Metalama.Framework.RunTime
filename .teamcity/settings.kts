// This file is automatically generated by `Build.ps1 generate-scripts`.

// Both Swabra and swabra need to be imported
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.sshAgent
import jetbrains.buildServer.configs.kotlin.buildFeatures.Swabra
import jetbrains.buildServer.configs.kotlin.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.failureConditions.*
import jetbrains.buildServer.configs.kotlin.triggers.*

version = "2024.03"

project {

    buildType(DebugBuild)
    buildType(ReleaseBuild)
    buildType(PublicBuild)
    buildType(PublicDeployment)
    buildType(DownstreamMerge)

    buildTypesOrder = arrayListOf(DebugBuild,ReleaseBuild,PublicBuild,PublicDeployment,DownstreamMerge)

}

object DebugBuild : BuildType({

    name = "Build [Debug]"

    artifactRules = "+:artifacts/publish/public/**/*=>artifacts/publish/public\n+:artifacts/publish/private/**/*=>artifacts/publish/private\n+:artifacts/testResults/**/*=>artifacts/testResults\n+:artifacts/logs/**/*=>logs\n"

    params {
        text("BuildArguments", "", label = "Build Arguments", description = "Arguments to append to the 'Build' build step.", allowEmpty = true)
        text("DefaultBranch", "develop/2024.1", label = "Default Branch", description = "The default branch of this build configuration.")
        text("TimeOut", "300", label = "Time-Out Threshold", description = "Seconds after the duration of the last successful build.", regex = """\d+""", validationMessage = "The timeout has to be an integer number.")
    }

    vcs {
        root(AbsoluteId("Metalama_Metalama20241_MetalamaFrameworkRunTime"))
    }

    steps {
        powerShell {
            name = "Kill background processes before cleanup"
            id = "PreKill"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "tools kill"
        }
        powerShell {
            name = "Build"
            id = "Build"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "test --configuration Debug --buildNumber %build.number% --buildType %system.teamcity.buildType.id% %BuildArguments%"
        }
        powerShell {
            name = "Kill background processes before next build"
            id = "PostKill"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "tools kill"
        }
    }

    failureConditions {
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.BUILD_DURATION
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = build {
                buildRule = lastSuccessful()
            }
            stopBuildOnFailure = true
            param("metricThreshold", "%TimeOut%")
        }
    }

    requirements {
        equals("env.BuildAgentType", "caravela04cloud")
    }

    features {
        swabra {
            lockingProcesses = Swabra.LockingProcessPolicy.KILL
            verbose = true
        }
    }

    triggers {
        vcs {
            watchChangesInDependencies = true
            branchFilter = "+:develop/2024.1"
            // Build will not trigger automatically if the commit message contains comment value.
            triggerRules = "-:comment=<<VERSION_BUMP>>|<<DEPENDENCIES_UPDATED>>:**"
        }
    }

})

object ReleaseBuild : BuildType({

    name = "Build [Release]"

    artifactRules = "+:artifacts/publish/public/**/*=>artifacts/publish/public\n+:artifacts/publish/private/**/*=>artifacts/publish/private\n+:artifacts/testResults/**/*=>artifacts/testResults\n+:artifacts/logs/**/*=>logs\n"

    params {
        text("BuildArguments", "", label = "Build Arguments", description = "Arguments to append to the 'Build' build step.", allowEmpty = true)
        text("DefaultBranch", "develop/2024.1", label = "Default Branch", description = "The default branch of this build configuration.")
        text("TimeOut", "300", label = "Time-Out Threshold", description = "Seconds after the duration of the last successful build.", regex = """\d+""", validationMessage = "The timeout has to be an integer number.")
    }

    vcs {
        root(AbsoluteId("Metalama_Metalama20241_MetalamaFrameworkRunTime"))
    }

    steps {
        powerShell {
            name = "Kill background processes before cleanup"
            id = "PreKill"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "tools kill"
        }
        powerShell {
            name = "Build"
            id = "Build"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "test --configuration Release --buildNumber %build.number% --buildType %system.teamcity.buildType.id% %BuildArguments%"
        }
        powerShell {
            name = "Kill background processes before next build"
            id = "PostKill"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "tools kill"
        }
    }

    failureConditions {
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.BUILD_DURATION
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = build {
                buildRule = lastSuccessful()
            }
            stopBuildOnFailure = true
            param("metricThreshold", "%TimeOut%")
        }
    }

    requirements {
        equals("env.BuildAgentType", "caravela04cloud")
    }

    features {
        swabra {
            lockingProcesses = Swabra.LockingProcessPolicy.KILL
            verbose = true
        }
    }

})

object PublicBuild : BuildType({

    name = "Build [Public]"

    artifactRules = "+:artifacts/publish/public/**/*=>artifacts/publish/public\n+:artifacts/publish/private/**/*=>artifacts/publish/private\n+:artifacts/testResults/**/*=>artifacts/testResults\n+:artifacts/logs/**/*=>logs\n"

    params {
        text("BuildArguments", "", label = "Build Arguments", description = "Arguments to append to the 'Build' build step.", allowEmpty = true)
        text("DefaultBranch", "develop/2024.1", label = "Default Branch", description = "The default branch of this build configuration.")
        text("TimeOut", "300", label = "Time-Out Threshold", description = "Seconds after the duration of the last successful build.", regex = """\d+""", validationMessage = "The timeout has to be an integer number.")
    }

    vcs {
        root(AbsoluteId("Metalama_Metalama20241_MetalamaFrameworkRunTime"))
    }

    steps {
        powerShell {
            name = "Kill background processes before cleanup"
            id = "PreKill"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "tools kill"
        }
        powerShell {
            name = "Build"
            id = "Build"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "test --configuration Public --buildNumber %build.number% --buildType %system.teamcity.buildType.id% %BuildArguments%"
        }
        powerShell {
            name = "Kill background processes before next build"
            id = "PostKill"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "tools kill"
        }
    }

    failureConditions {
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.BUILD_DURATION
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = build {
                buildRule = lastSuccessful()
            }
            stopBuildOnFailure = true
            param("metricThreshold", "%TimeOut%")
        }
    }

    requirements {
        equals("env.BuildAgentType", "caravela04cloud")
    }

    features {
        swabra {
            lockingProcesses = Swabra.LockingProcessPolicy.KILL
            verbose = true
        }
    }

})

object PublicDeployment : BuildType({

    name = "Deploy [Public]"

    type = Type.DEPLOYMENT

    params {
        text("PublishArguments", "", label = "Publish Arguments", description = "Arguments to append to the 'Publish' build step.", allowEmpty = true)
        text("DefaultBranch", "release/2024.1", label = "Default Branch", description = "The default branch of this build configuration.")
        text("TimeOut", "300", label = "Time-Out Threshold", description = "Seconds after the duration of the last successful build.", regex = """\d+""", validationMessage = "The timeout has to be an integer number.")
    }

    vcs {
        root(AbsoluteId("Metalama_Metalama20241_MetalamaFrameworkRunTime"))
    }

    steps {
        powerShell {
            name = "Publish"
            id = "Publish"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "publish --configuration Public %PublishArguments%"
        }
    }

    failureConditions {
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.BUILD_DURATION
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = build {
                buildRule = lastSuccessful()
            }
            stopBuildOnFailure = true
            param("metricThreshold", "%TimeOut%")
        }
    }

    requirements {
        equals("env.BuildAgentType", "caravela04cloud")
    }

    features {
        swabra {
            lockingProcesses = Swabra.LockingProcessPolicy.KILL
            verbose = true
        }
        sshAgent {
            // By convention, the SSH key name is always PostSharp.Engineering for all repositories using SSH to connect.
            teamcitySshKey = "PostSharp.Engineering"
        }
    }

    dependencies {
        dependency(PublicBuild) {
            snapshot {
                     onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = "+:artifacts/publish/public/**/*=>artifacts/publish/public\n+:artifacts/publish/private/**/*=>artifacts/publish/private"
            }
        }
     }

})

object DownstreamMerge : BuildType({

    name = "Downstream Merge"

    params {
        text("DownstreamMergeArguments", "", label = "Merge downstream Arguments", description = "Arguments to append to the 'Merge downstream' build step.", allowEmpty = true)
        text("DefaultBranch", "develop/2024.1", label = "Default Branch", description = "The default branch of this build configuration.")
        text("TimeOut", "300", label = "Time-Out Threshold", description = "Seconds after the duration of the last successful build.", regex = """\d+""", validationMessage = "The timeout has to be an integer number.")
    }

    vcs {
        root(AbsoluteId("Metalama_Metalama20241_MetalamaFrameworkRunTime"))
    }

    steps {
        powerShell {
            name = "Merge downstream"
            id = "DownstreamMerge"
            scriptMode = file {
                path = "Build.ps1"
            }
            noProfile = false
            scriptArgs = "tools git merge-downstream %DownstreamMergeArguments%"
        }
    }

    failureConditions {
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.BUILD_DURATION
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = build {
                buildRule = lastSuccessful()
            }
            stopBuildOnFailure = true
            param("metricThreshold", "%TimeOut%")
        }
    }

    requirements {
        equals("env.BuildAgentType", "caravela04cloud")
    }

    features {
        swabra {
            lockingProcesses = Swabra.LockingProcessPolicy.KILL
            verbose = true
        }
        sshAgent {
            // By convention, the SSH key name is always PostSharp.Engineering for all repositories using SSH to connect.
            teamcitySshKey = "PostSharp.Engineering"
        }
    }

    triggers {
        vcs {
            watchChangesInDependencies = true
            branchFilter = "+:develop/2024.1"
            // Build will not trigger automatically if the commit message contains comment value.
            triggerRules = "-:comment=<<VERSION_BUMP>>|<<DEPENDENCIES_UPDATED>>:**"
        }
    }

    dependencies {
        dependency(DebugBuild) {
            snapshot {
                     onDependencyFailure = FailureAction.FAIL_TO_START
            }
        }
     }

})

