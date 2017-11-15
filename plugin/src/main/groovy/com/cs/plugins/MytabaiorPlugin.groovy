package com.cs.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by 80657 on 2017/11/15.
 */
class MytabaiorPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
//        project.extensions.create("mybatisGenerator",MybatisGeneratorExtension)
//        project.task("mybatisTask",type: MybatisTask);
        project.logger.info "Configuring Mybatis Generator for project: $project.name"
        MybatisTask task = project.tasks.create("mbGenerator", MybatisTask);
        project.configurations.create('mybatisGenerator').with {
            description = 'The cargo libraries to be used for this project.'
        }
        project.extensions.create("mybatisGenerator", MybatisGeneratorExtension)
        task.conventionMapping.with {
            mybatisGeneratorClasspath = {
                def config = project.configurations.getAt('mybatisGenerator')
                if (config.dependencies.empty) {
                    project.dependencies {
                        mybatisGenerator 'org.mybatis.generator:mybatis-generator-core:1.3.2'
                        mybatisGenerator 'mysql:mysql-connector-java:5.1.36'
                        mybatisGenerator 'tk.mybatis:mapper:3.3.2'
                    }
                }
                config
            }
            overwrite = { project.mybatisGenerator.overwrite }
            configFile = { project.mybatisGenerator.configFile }
            verbose = { project.mybatisGenerator.verbose }
            targetDir = { project.mybatisGenerator.targetDir }
        }
    }
}
//gradle mybatisTask
