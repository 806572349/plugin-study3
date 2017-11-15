package com.cs.plugins

import groovy.transform.ToString

/**
 * Created by 80657 on 2017/11/15.
 */
@ToString(includeNames = true)
class MybatisGeneratorExtension {
    def overwrite = true
    def configFile = "generatorConfig.xml"
    def verbose = false
    def targetDir = "."
}
