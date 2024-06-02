import java.util.*;

// Class representing Software
class Software {
    private String softwareId;
    private String name;
    private String version;
    private double pricePerLicense;
    private String vendorId;

    public Software(String softwareId, String name, String version, double pricePerLicense, String vendorId) {
        this.softwareId = softwareId;
        this.name = name;
        this.version = version;
        this.pricePerLicense = pricePerLicense;
        this.vendorId = vendorId;
    }

    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getPricePerLicense() {
        return pricePerLicense;
    }

    public void setPricePerLicense(double pricePerLicense) {
        this.pricePerLicense = pricePerLicense;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}

// Class representing Device
class Device {
    private String deviceId;
    private String employeeId;

    public Device(String deviceId, String employeeId) {
        this.deviceId = deviceId;
        this.employeeId = employeeId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}

// Class representing Employee
class Employee {
    private String employeeId;
    private String name;

    public Employee(String employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// Class representing Vendor
class Vendor {
    private String vendorId;
    private String name;

    public Vendor(String vendorId, String name) {
        this.vendorId = vendorId;
        this.name = name;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// Class representing Installation
class Installation {
    private String installationId;
    private String softwareId;
    private String deviceId;
    private Date installationDate;
    private Date licenseExpiryDate;

    public Installation(String installationId, String softwareId, String deviceId, Date installationDate, Date licenseExpiryDate) {
        this.installationId = installationId;
        this.softwareId = softwareId;
        this.deviceId = deviceId;
        this.installationDate = installationDate;
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }

    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public Date getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(Date licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }
}

// Class for managing the asset system
class AssetManagementSystem {
    private Map<String, Software> softwares = new HashMap<>();
    private Map<String, Device> devices = new HashMap<>();
    private Map<String, Employee> employees = new HashMap<>();
    private Map<String, Vendor> vendors = new HashMap<>();
    private Map<String, List<Installation>> installations = new HashMap<>();

    public void addSoftware(Software software) {
        softwares.put(software.getSoftwareId(), software);
    }

    public void addDevice(Device device) {
        devices.put(device.getDeviceId(), device);
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
    }

    public void addVendor(Vendor vendor) {
        vendors.put(vendor.getVendorId(), vendor);
    }

    public void addInstallation(Installation installation) {
        if (!installations.containsKey(installation.getSoftwareId())) {
            installations.put(installation.getSoftwareId(), new ArrayList<>());
        }
        installations.get(installation.getSoftwareId()).add(installation);
    }

    public int getNumberOfInstallations(String softwareId, Date startDate, Date endDate) {
        int count = 0;
        if (installations.containsKey(softwareId)) {
            for (Installation installation : installations.get(softwareId)) {
                if (!installation.getInstallationDate().before(startDate) && !installation.getInstallationDate().after(endDate)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getNumberOfSoftwareInDevice(String deviceId, Date startDate, Date endDate) {
        int count = 0;
        for (List<Installation> installationList : installations.values()) {
            for (Installation installation : installationList) {
                if (installation.getDeviceId().equals(deviceId) && 
                    !installation.getInstallationDate().before(startDate) && 
                    !installation.getInstallationDate().after(endDate)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getNumberOfSoftwareForEmployee(String employeeId, Date startDate, Date endDate) {
        int count = 0;
        for (List<Installation> installationList : installations.values()) {
            for (Installation installation : installationList) {
                Device device = devices.get(installation.getDeviceId());
                if (device.getEmployeeId().equals(employeeId) && 
                    !installation.getInstallationDate().before(startDate) && 
                    !installation.getInstallationDate().after(endDate)) {
                    count++;
                }
            }
        }
        return count;
    }

    public double getAmountSpentForSoftware(String softwareId, Date startDate, Date endDate) {
        double totalAmount = 0;
        if (installations.containsKey(softwareId)) {
            Software software = softwares.get(softwareId);
            for (Installation installation : installations.get(softwareId)) {
                if (!installation.getInstallationDate().before(startDate) && !installation.getInstallationDate().after(endDate)) {
                    totalAmount += software.getPricePerLicense();
                }
            }
        }
        return totalAmount;
    }

    public double getAmountSpentForEmployee(String employeeId, Date startDate, Date endDate) {
        double totalAmount = 0;
        for (List<Installation> installationList : installations.values()) {
            for (Installation installation : installationList) {
                Device device = devices.get(installation.getDeviceId());
                if (device.getEmployeeId().equals(employeeId) && 
                    !installation.getInstallationDate().before(startDate) && 
                    !installation.getInstallationDate().after(endDate)) {
                    Software software = softwares.get(installation.getSoftwareId());
                    totalAmount += software.getPricePerLicense();
                }
            }
        }
        return totalAmount;
    }

    public double getAmountSpentOnVendor(String vendorId, Date startDate, Date endDate) {
        double totalAmount = 0;
        for (Software software : softwares.values()) {
            if (software.getVendorId().equals(vendorId)) {
                totalAmount += getAmountSpentForSoftware(software.getSoftwareId(), startDate, endDate);
            }
        }
        return totalAmount;
    }

    public int getNumberOfInstallationsFromVendor(String vendorId, Date startDate, Date endDate) {
        int count = 0;
        for (Software software : softwares.values()) {
            if (software.getVendorId().equals(vendorId)) {
                count += getNumberOfInstallations(software.getSoftwareId(), startDate, endDate);
            }
        }
        return count;
    }

    public List<String> getDevicesWithExpiredSoftware(Date currentDate) {
        List<String> expiredDevices = new ArrayList<>();
        for (List<Installation> installationList : installations.values()) {
            for (Installation installation : installationList) {
                if (installation.getLicenseExpiryDate().before(currentDate)) {
                    expiredDevices.add(installation.getDeviceId());
                }
            }
        }
        return expiredDevices;
    }
}

// Main class to test the functionality
public class Main {
    public static void main(String[] args) {
        AssetManagementSystem ams = new AssetManagementSystem();
        
        // Add vendors
        Vendor vendor1 = new Vendor("V1", "Vendor One");
        ams.addVendor(vendor1);

        // Add software
        Software software1 = new Software("S1", "Software One", "1.0", 500.0, "V1");
        ams.addSoftware(software1);

        // Add employees
        Employee employee1 = new Employee("E1", "Employee One");
        ams.addEmployee(employee1);

        // Add devices
        Device device1 = new Device("D1", "E1");
        ams.addDevice(device1);

        // Add installations
        Installation installation1 = new Installation("I1", "S1", "D1", new Date(), new Date());
        ams.addInstallation(installation1);

        // Example to generate reports
        Date startDate = new Date(); // Adjust dates as needed
        Date endDate = new Date(); // Adjust dates as needed
        System.out.println("Number of installations of software S1: " + ams.getNumberOfInstallations("S1", startDate, endDate));
        System.out.println("Number of software installed in device D1: " + ams.getNumberOfSoftwareInDevice("D1", startDate, endDate));
        System.out.println("Amount spent for software S1: " + ams.getAmountSpentForSoftware("S1", startDate, endDate));
        System.out.println("Devices with expired software: " + ams.getDevicesWithExpiredSoftware(new Date()));
    }
}


