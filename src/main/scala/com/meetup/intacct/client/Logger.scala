package com.meetup.intacct.client

import org.slf4j.LoggerFactory

trait Logger {
  protected val Logger = LoggerFactory.getLogger(getClass.getName)
}