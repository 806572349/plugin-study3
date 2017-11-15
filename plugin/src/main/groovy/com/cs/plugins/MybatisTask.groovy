package com.cs.plugins

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.internal.project.IsolatedAntBuilder
import org.gradle.api.tasks.TaskAction

/**
 * Created by 80657 on 2017/11/15.
 */
class MybatisTask extends  DefaultTask{

    def overwrite
    def configFile
    def verbose
    def targetDir
    FileCollection mybatisGeneratorClasspath

        @TaskAction
        void Output(){
//            println  "url is ${project.mybatisGenerator.configFile},"

            services.get(IsolatedAntBuilder).withClasspath(getMybatisGeneratorClasspath()).execute {
                ant.taskdef(name: 'mbgenerator', classname: 'org.mybatis.generator.ant.GeneratorAntTask')

                ant.properties['generated.source.dir'] = getTargetDir()
                ant.mbgenerator(overwrite: getOverwrite(), configfile: getConfigFile(), verbose: getVerbose())
            }

        }

}
