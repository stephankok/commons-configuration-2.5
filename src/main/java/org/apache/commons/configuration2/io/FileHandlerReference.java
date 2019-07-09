package org.apache.commons.configuration2.io;


import java.io.File;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class FileHandlerReference {
	private final AtomicReference<FileLocator> fileLocator;

	public FileHandlerReference(FileLocator locator) {
		fileLocator = new AtomicReference<>(locator);
	}
	
	public String getEncoding() {
		return getFileLocator().getEncoding();
	}

	public void setFileLocator(final FileLocator locator) {
		fileLocator.set(locator);
	}
	
	public boolean compareAndSet(FileLocator expect, FileLocator update) {
		return fileLocator.compareAndSet(expect, update);
	}
		

	/**
	* Returns a  {@code  FileLocator}  object with the specification of the file stored by this  {@code  FileHandler} . Note that this method returns the internal data managed by this  {@code  FileHandler}  as it was defined. This is not necessarily the same as the data returned by the single access methods like  {@code  getFileName()}  or  {@code  getURL()} : These methods try to derive missing data from other values that have been set.
	* @return  a  {@code  FileLocator}  with the referenced file
	*/
	public FileLocator getFileLocator() {
		return fileLocator.get();
	}

	/**
	* Locates the referenced file if necessary and ensures that the associated {@link FileLocator}  is fully initialized. When accessing the referenced file the information stored in the associated  {@code  FileLocator}  is used. If this information is incomplete (e.g. only the file name is set), an attempt to locate the file may have to be performed on each access. By calling this method such an attempt is performed once, and the results of a successful localization are stored. Hence, later access to the referenced file can be more efficient. Also, all properties pointing to the referenced file in this object's  {@code  FileLocator}  are set (i.e. the URL, the base path, and the file name). If the referenced file cannot be located, result is <b>false</b>. This means that the information in the current  {@code  FileLocator}  is insufficient or wrong. If the {@code  FileLocator}  is already fully defined, it is not changed.
	* @return  a flag whether the referenced file could be located successfully
	* @see FileLocatorUtils#fullyInitializedLocator(FileLocator)
	*/
	public boolean locate() {
		boolean result;
		boolean done;
		do {
			final FileLocator locator = getFileLocator();
			FileLocator fullLocator = FileLocatorUtils.fullyInitializedLocator(locator);
			if (fullLocator == null) {
				result = false;
				fullLocator = locator;
			} else {
				result = fullLocator != locator || FileLocatorUtils.isFullyInitialized(locator);
			}
			done = fileLocator.compareAndSet(locator, fullLocator);
		} while (!done);
		return result;
	}

	/**
	* Tests whether a location is defined for this  {@code  FileHandler} .
	* @return  <b>true</b> if a location is defined, <b>false</b> otherwise
	*/
	public boolean isLocationDefined() {
		return FileLocatorUtils.isLocationDefined(getFileLocator());
	}

	/**
	* Returns the  {@code  FileSystem}  to be used by this object when locating files. Result is never <b>null</b>; if no file system has been set, the default file system is returned.
	* @return  the used  {@code  FileSystem}
	*/
	public FileSystem getFileSystem() {
		return FileLocatorUtils.obtainFileSystem(getFileLocator());
	}

	/**
	* Returns the  {@code  FileLocationStrategy}  to be applied when accessing the associated file. This method never returns <b>null</b>. If a {@code  FileLocationStrategy}  has been set, it is returned. Otherwise, result is the default  {@code  FileLocationStrategy} .
	* @return  the  {@code  FileLocationStrategy}  to be used
	*/
	public FileLocationStrategy getLocationStrategy() {
		return FileLocatorUtils.obtainLocationStrategy(getFileLocator());
	}
	
	/**
     * Return the name of the file. If only a URL is defined, the file name
     * is derived from there.
     *
     * @return the file name
     */
    public String getFileName()
    {
        final FileLocator locator = getFileLocator();
        if (locator.getFileName() != null)
        {
            return locator.getFileName();
        }

        if (locator.getSourceURL() != null)
        {
            return FileLocatorUtils.getFileName(locator.getSourceURL());
        }

        return null;
    }
    
    /**
     * Return the base path. If no base path is defined, but a URL, the base
     * path is derived from there.
     *
     * @return the base path
     */
    public String getBasePath()
    {
        final FileLocator locator = getFileLocator();
        if (locator.getBasePath() != null)
        {
            return locator.getBasePath();
        }

        if (locator.getSourceURL() != null)
        {
            return FileLocatorUtils.getBasePath(locator.getSourceURL());
        }

        return null;
    }
    
    /**
     * Returns the full path to the associated file. The return value is a valid
     * {@code File} path only if this location is based on a file on the local
     * disk. If the file was loaded from a packed archive, the returned value is
     * the string form of the URL from which the file was loaded.
     *
     * @return the full path to the associated file
     */
    public String getPath()
    {
        final FileLocator locator = getFileLocator();
        final File file = createFile(locator);
        return FileLocatorUtils.obtainFileSystem(locator).getPath(file,
                locator.getSourceURL(), locator.getBasePath(), locator.getFileName());
    }
    
    /**
     * Returns the location of the associated file as a URL. If a URL is set,
     * it is directly returned. Otherwise, an attempt to locate the referenced
     * file is made.
     *
     * @return a URL to the associated file; can be <b>null</b> if the location
     *         is unspecified
     */
    public URL getURL()
    {
        final FileLocator locator = getFileLocator();
        return (locator.getSourceURL() != null) ? locator.getSourceURL()
                : FileLocatorUtils.locate(locator);
    }
    
    public File createFile() {
    	return createFile(getFileLocator());
    }
    
    /**
     * Creates a {@code File} object from the content of the given
     * {@code FileLocator} object. If the locator is not defined, result is
     * <b>null</b>.
     *
     * @param loc the {@code FileLocator}
     * @return a {@code File} object pointing to the associated file
     */
    public static File createFile(final FileLocator loc)
    {
        if (loc.getFileName() == null && loc.getSourceURL() == null)
        {
            return null;
        }
        else if (loc.getSourceURL() != null)
        {
            return FileLocatorUtils.fileFromURL(loc.getSourceURL());
        }
        else
        {
            return FileLocatorUtils.getFile(loc.getBasePath(),
                    loc.getFileName());
        }
    }
}