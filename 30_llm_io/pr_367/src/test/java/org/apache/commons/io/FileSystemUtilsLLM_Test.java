package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.time.Duration;
import org.junit.jupiter.api.Test;

public class FileSystemUtilsLLM_Test {

    private static final Duration NEG_1_TIMEOUT = Duration.ofMillis(-1);

    @Test
    public void testPerformCommand_InvalidCommand() {
        final FileSystemUtils fsu = new FileSystemUtils() {
            @Override
            Process openProcess(final String[] cmdAttribs) throws IOException {
                throw new IOException("Invalid command");
            }
        };
        assertThrows(IOException.class, () -> fsu.performCommand(new String[]{"invalidCommand"}, 1, NEG_1_TIMEOUT));
    }

    @Test
    public void testPerformCommand_Timeout() {
        final FileSystemUtils fsu = new FileSystemUtils() {
            @Override
            Process openProcess(final String[] cmdAttribs) throws IOException {
                return new Process() {
                    @Override
                    public void destroy() {
                    }

                    @Override
                    public int exitValue() {
                        return 0;
                    }

                    @Override
                    public InputStream getErrorStream() {
                        return null;
                    }

                    @Override
                    public InputStream getInputStream() {
                        return new ByteArrayInputStream(new byte[0]);
                    }

                    @Override
                    public OutputStream getOutputStream() {
                        return null;
                    }

                    @Override
                    public int waitFor() throws InterruptedException {
                        Thread.sleep(2000); // Simulate a delay
                        return 0;
                    }
                };
            }
        };
        assertThrows(IOException.class, () -> fsu.performCommand(new String[]{"cmd"}, 1, Duration.ofMillis(100)));
    }

    @Test
    public void testPerformCommand_EmptyResponse() {
        final FileSystemUtils fsu = new FileSystemUtils() {
            @Override
            Process openProcess(final String[] cmdAttribs) throws IOException {
                return new Process() {
                    @Override
                    public void destroy() {
                    }

                    @Override
                    public int exitValue() {
                        return 0;
                    }

                    @Override
                    public InputStream getErrorStream() {
                        return null;
                    }

                    @Override
                    public InputStream getInputStream() {
                        return new ByteArrayInputStream(new byte[0]);
                    }

                    @Override
                    public OutputStream getOutputStream() {
                        return null;
                    }

                    @Override
                    public int waitFor() throws InterruptedException {
                        return 0;
                    }
                };
            }
        };
        assertThrows(IOException.class, () -> fsu.performCommand(new String[]{"cmd"}, 1, NEG_1_TIMEOUT));
    }

    @Test
    public void testPerformCommand_NonZeroExitCode() {
        final FileSystemUtils fsu = new FileSystemUtils() {
            @Override
            Process openProcess(final String[] cmdAttribs) throws IOException {
                return new Process() {
                    @Override
                    public void destroy() {
                    }

                    @Override
                    public int exitValue() {
                        return 1;
                    }

                    @Override
                    public InputStream getErrorStream() {
                        return null;
                    }

                    @Override
                    public InputStream getInputStream() {
                        return new ByteArrayInputStream(new byte[0]);
                    }

                    @Override
                    public OutputStream getOutputStream() {
                        return null;
                    }

                    @Override
                    public int waitFor() throws InterruptedException {
                        return 1;
                    }
                };
            }
        };
        assertThrows(IOException.class, () -> fsu.performCommand(new String[]{"cmd"}, 1, NEG_1_TIMEOUT));
    }
}