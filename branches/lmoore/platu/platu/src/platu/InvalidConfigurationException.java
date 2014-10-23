/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package platu;

import platu.lpn.io.*;

/**
 *
 * @author Larry Moore
 */
public class InvalidConfigurationException extends RuntimeException {
    public InvalidConfigurationException(String message) {
        super(message);
    }

    public InvalidConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}
