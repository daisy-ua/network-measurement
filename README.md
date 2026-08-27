## Configuration

The configuration is hosted as a [**GitHub Raw file**](https://raw.githubusercontent.com/daisy-ua/network-measurement/main/remote-config/config.json).

```json
{
  "mode": "speed_test"
}
```
**Supported modes:** `speed_test`, `ping_test`

## Notes
- **Interface for DataSources:**
  Used interfaces for components with complex state and logic (`SpeedTestRemoteDataSource`), but kept simple pass-throughs concrete (`TestMode*DataSource`) to eliminate unnecessary boilerplate.

- **Speed test lifecycle:**
  Stopped the active test when leaving the screen to avoid unnecessary network traffic, but configuration changes also stop the test as a known limitation.

- **Speed test edge cases:**
  Focused on the main flow; more complex network conditions were not fully tested or handled due to the limited implementation time.
