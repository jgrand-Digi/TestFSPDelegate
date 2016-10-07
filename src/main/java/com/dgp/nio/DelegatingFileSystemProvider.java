package com.dgp.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessMode;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * A FileSystemProvider that wraps a delegate.
 * @author jeremy.grand
 *
 */
public class DelegatingFileSystemProvider extends FileSystemProvider {

    private final FileSystemProvider delegate;

    public DelegatingFileSystemProvider(FileSystemProvider in_fileSystemProvider) {
        delegate = in_fileSystemProvider;
    }

    public FileSystem newFileSystem(URI in_uri, Map<String, ?> in_env) throws IOException {
        return new MyFileSystem(this,delegate.newFileSystem(in_uri, in_env));
    }

    public FileSystem getFileSystem(URI in_uri) {
        return new MyFileSystem(this,delegate.getFileSystem(in_uri));
    }
    
    public FileSystem newFileSystem(Path in_path, Map<String, ?> in_env) throws IOException {
        return new MyFileSystem(this,delegate.newFileSystem(in_path, in_env));
    }
    
    // Delegate methods
    public String getScheme() {
        return delegate.getScheme();
    }
    
    public String toString() {
        return delegate.toString();
    }

    public Path getPath(URI in_uri) {
        return delegate.getPath(in_uri);
    }

    public InputStream newInputStream(Path in_path, OpenOption... in_options) throws IOException {
        return delegate.newInputStream(in_path, in_options);
    }

    public OutputStream newOutputStream(Path in_path, OpenOption... in_options) throws IOException {
        return delegate.newOutputStream(in_path, in_options);
    }

    public FileChannel newFileChannel(Path in_path, Set<? extends OpenOption> in_options, FileAttribute<?>... in_attrs)
            throws IOException {
        return delegate.newFileChannel(in_path, in_options, in_attrs);
    }

    public AsynchronousFileChannel newAsynchronousFileChannel(Path in_path, Set<? extends OpenOption> in_options,
            ExecutorService in_executor, FileAttribute<?>... in_attrs) throws IOException {
        return delegate.newAsynchronousFileChannel(in_path, in_options, in_executor, in_attrs);
    }

    public SeekableByteChannel newByteChannel(Path in_path, Set<? extends OpenOption> in_options,
            FileAttribute<?>... in_attrs) throws IOException {
        return delegate.newByteChannel(in_path, in_options, in_attrs);
    }

    public DirectoryStream<Path> newDirectoryStream(Path in_dir, Filter<? super Path> in_filter) throws IOException {
        return delegate.newDirectoryStream(in_dir, in_filter);
    }

    public void createDirectory(Path in_dir, FileAttribute<?>... in_attrs) throws IOException {
        delegate.createDirectory(in_dir, in_attrs);
    }

    public void createSymbolicLink(Path in_link, Path in_target, FileAttribute<?>... in_attrs) throws IOException {
        delegate.createSymbolicLink(in_link, in_target, in_attrs);
    }

    public void createLink(Path in_link, Path in_existing) throws IOException {
        delegate.createLink(in_link, in_existing);
    }

    public void delete(Path in_path) throws IOException {
        delegate.delete(in_path);
    }

    public boolean deleteIfExists(Path in_path) throws IOException {
        return delegate.deleteIfExists(in_path);
    }

    public Path readSymbolicLink(Path in_link) throws IOException {
        return delegate.readSymbolicLink(in_link);
    }

    public void copy(Path in_source, Path in_target, CopyOption... in_options) throws IOException {
        delegate.copy(in_source, in_target, in_options);
    }

    public void move(Path in_source, Path in_target, CopyOption... in_options) throws IOException {
        delegate.move(in_source, in_target, in_options);
    }

    public boolean isSameFile(Path in_path, Path in_path2) throws IOException {
        return delegate.isSameFile(in_path, in_path2);
    }

    public boolean isHidden(Path in_path) throws IOException {
        return delegate.isHidden(in_path);
    }

    public FileStore getFileStore(Path in_path) throws IOException {
        return delegate.getFileStore(in_path);
    }

    public void checkAccess(Path in_path, AccessMode... in_modes) throws IOException {
        delegate.checkAccess(in_path, in_modes);
    }

    public <V extends FileAttributeView> V getFileAttributeView(Path in_path, Class<V> in_type,
            LinkOption... in_options) {
        return delegate.getFileAttributeView(in_path, in_type, in_options);
    }

    public <A extends BasicFileAttributes> A readAttributes(Path in_path, Class<A> in_type, LinkOption... in_options)
            throws IOException {
        return delegate.readAttributes(in_path, in_type, in_options);
    }

    public Map<String, Object> readAttributes(Path in_path, String in_attributes, LinkOption... in_options)
            throws IOException {
        return delegate.readAttributes(in_path, in_attributes, in_options);
    }

    public void setAttribute(Path in_path, String in_attribute, Object in_value, LinkOption... in_options)
            throws IOException {
        delegate.setAttribute(in_path, in_attribute, in_value, in_options);
    }

    
}
