package com.dgp.nio;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.WatchService;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Set;

public class MyFileSystem extends FileSystem {

    private final FileSystemProvider provider;
    private final FileSystem delegate;
    
//    to have something to customize
    private String message;
    
    public MyFileSystem(FileSystemProvider in_fileSystemProvider, FileSystem in_fileSystem) {
        provider = in_fileSystemProvider;
        delegate = in_fileSystem;
    }
    
//    Method Override
    @Override
    public String toString() {
        return getMessage();
    }
    
//    Getter & Setter
    public String getMessage() {
        return message;
    }
    public void setMessage(String in_name) {
        message = in_name;
    }
    
    
//  Delegate methods
    @Override
    public FileSystemProvider provider() {
        return provider;
    }

    public void close() throws IOException {
        delegate.close();
    }

    public boolean isOpen() {
        return delegate.isOpen();
    }

    public boolean isReadOnly() {
        return delegate.isReadOnly();
    }

    public String getSeparator() {
        return delegate.getSeparator();
    }

    public Iterable<Path> getRootDirectories() {
        return delegate.getRootDirectories();
    }

    public Iterable<FileStore> getFileStores() {
        return delegate.getFileStores();
    }

    public Set<String> supportedFileAttributeViews() {
        return delegate.supportedFileAttributeViews();
    }

    public Path getPath(String in_first, String... in_more) {
        return delegate.getPath(in_first, in_more);
    }

    public PathMatcher getPathMatcher(String in_syntaxAndPattern) {
        return delegate.getPathMatcher(in_syntaxAndPattern);
    }

    public UserPrincipalLookupService getUserPrincipalLookupService() {
        return delegate.getUserPrincipalLookupService();
    }

    public WatchService newWatchService() throws IOException {
        return delegate.newWatchService();
    }
}
