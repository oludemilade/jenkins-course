job('NodeJS Docker example') {
    scm {
        git('https://github.com/oludemilade/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('oludemilade')
            node / gitConfigEmail('oludemiladeraji@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('rajiano/sony_interview')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('rajiano')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
