package com.example.contester.generator.osmo

import osmo.tester.OSMOConfiguration
import osmo.tester.generator.MainGenerator
import osmo.tester.generator.testsuite.TestSuite
import osmo.tester.model.FSMTransition
import osmo.tester.model.InvocationTarget

class MyMainGenerator(seed: Long, suite: TestSuite?, config: OSMOConfiguration?) : MainGenerator(seed, suite, config) {

    override fun invokeAll(targets: MutableCollection<InvocationTarget>) {
        targets.forEach { println(it) }
    }

    override fun execute(transition: FSMTransition) {

        val step = suite.addStep(transition)
        step.start()
        invokeAll(transition!!.preMethods, "pre", transition)
        suite.storeGeneralState(fsm)
        invokeAll(transition!!.postMethods, "post", transition)
        step.end()
        suite.addStepStat(step)
    }

}