package com.betrybe.museumfinder.exception;

/**
 * InvalidCoordinateException.
 */
public class InvalidCoordinateException extends RuntimeException {

  public InvalidCoordinateException(String message) {
    super(message);
  }

  public InvalidCoordinateException(String message, Throwable cause) {
    super(message, cause);
  }
}
