#!/usr/bin/env groovy

import jenkins.model.*
import com.sonyericsson.jenkins.plugins.bfa.model.FailureCause
import com.sonyericsson.jenkins.plugins.bfa.model.indication.*
import com.sonyericsson.jenkins.plugins.bfa.model.FailureCauseModification

println("=== Configure Build Failure Analyzer")

def bfaPlugin = com.sonyericsson.jenkins.plugins.bfa.PluginImpl.getInstance()
def knowledgeBase = bfaPlugin.getKnowledgeBase()


def failureCauses = [
    [
        'name':         'Warning treated as error',
        'description':  'Build treats warnings as errors. Please see the log and fix the warnings.',
        'comment':      '',
        'categories':   ['compilation'],
        'indications':  [
            new BuildLogIndication('^.*warnings being treated as errors.*')
        ]
    ],
    [
        'name':         'C++ compilation error',
        'description':  'The C++ code did not compile.',
        'comment':      '',
        'categories':   ['compilation'],
        'indications':  [
            new BuildLogIndication('(.*):\\d+:\\d+: error:.*'),
            new BuildLogIndication('.*\\(\\d+\\) : error C\\d+: .*'),
            new BuildLogIndication('.*fatal error C\\d+: .*'),
            new BuildLogIndication('.*:\\d+: error: .*'),
            new BuildLogIndication('.*\\(\\d+\\): error:.*'),
            new BuildLogIndication('.+(\\.cpp|\\.h):.+undefined reference to\\s.+')
        ]
    ],
]

failureCauses.collect {
    if (!(it.name in knowledgeBase.causes.collect { it.getName() })) {
        FailureCause f = new FailureCause(it.name, it.description, it.comment)
        f.initModifications()
        it.indications.each {
            f.addIndication(it)
        }
        f.setCategories(it.categories)
        knowledgeBase.saveCause(f)
    }
}
